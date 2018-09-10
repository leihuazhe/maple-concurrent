package com.may.two;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

/**
 * @param <V>
 * @author youjie
 */
public class MapToProForkJoin<V> extends RecursiveTask {

    private static final long serialVersionUID = 1L;
    /**
     * 分割阈值
     */
    private static final int THRESHOLD = 2;

    private List<Map<String, Object>> datas;

    private Class<V> classType;


    /**
     * @param datas
     * @param classType
     */
    public MapToProForkJoin(List<Map<String, Object>> datas, Class<V> classType) {
        this.classType = classType;
        this.datas = datas;
    }

    @Override
    protected Object compute() {
        List<V> list = new ArrayList<>();
        if (datas.size() < THRESHOLD) {
            try {
                for (Map<String, Object> map : datas) {
                    V object = classType.newInstance();
                    Field[] fields = classType.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                        if (annotation != null && map.containsKey(annotation.value())) {
                            field.set(object, map.get(annotation.value()));
                        }
                    }
                    list.add(object);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {

            List<MapToProForkJoin> tasks = new ArrayList<>();
            for (int index = 0; index * THRESHOLD < datas.size(); index++) {
                MapToProForkJoin task;
                if ((index + 1) * THRESHOLD > datas.size()) {
                    task = new MapToProForkJoin(datas.subList(index * THRESHOLD, datas.size()), classType);
                } else {
                    task = new MapToProForkJoin(datas.subList(index * THRESHOLD, (index + 1) * THRESHOLD), classType);
                }
                task.fork();
                tasks.add(task);
            }
            for (MapToProForkJoin task : tasks) {
                list.addAll((List<V>) task.join());
            }
        }

        return list;
    }

}
