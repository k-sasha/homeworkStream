package lambda.worker;

@FunctionalInterface
public interface OnTaskErrorListener {
    void onError(String resultError);
}
