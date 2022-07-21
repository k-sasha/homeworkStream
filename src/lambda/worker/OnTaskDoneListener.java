package lambda.worker;

@FunctionalInterface
public interface OnTaskDoneListener {
    void onDone(String result);
}
