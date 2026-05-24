package utils;

public class TestResultManager {
	
	private static ThreadLocal<Integer> row = new ThreadLocal<>();

    public static void setRow(int rowNum) {
        row.set(rowNum);
    }

    public static Integer getRow() {
        return row.get();
    }
}
