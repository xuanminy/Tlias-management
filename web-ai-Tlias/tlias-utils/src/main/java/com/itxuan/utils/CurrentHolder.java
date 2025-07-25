package com.itxuan.utils;

public class CurrentHolder {
    private static ThreadLocal<Integer> currentEmpId = new ThreadLocal<>();
    public static void setCurrentEmpId(Integer empId) {
        currentEmpId.set(empId);
    }
    public static Integer getCurrentEmpId() {
        return currentEmpId.get();
    }
    public static void remove() {
        currentEmpId.remove();
    }
}
