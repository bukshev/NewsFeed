package ru.nsu.fit.mobdev.domain;

/**
 * Абстракция для всех UseCase'ов приложения.
 *
 * @param <RequestParameterType> Здесь будет конкретный тип для входных параметров в UseCase —
 *                              то, что необходимо для работы на уровне Repository и ниже.
 *
 * @param <ResponseType> Здесь будет конкретный тип для выходных данных из UseCase —
 *                      то, что будет приходить из Repository (и, с другой стороны то, что ожидается на уровне Presenter).
 */
public abstract class UseCase<RequestParameterType, ResponseType> {

    /**
     * Response мы получаем не сразу, поэтому нам тут нужно иметь некий механизм (в данном случае — это замыкание),
     * в котором будут методы, которые определяться на уровне Presenter, а вызываться на уровне самого UseCase.
     * Другими словами: Так презентер может описать "что делать с результатами выполнения UseCase'а".
     * @param <DataType> Тип данных, который ожидается на уровне Presenter'а в случае успеха.
     */
    public interface IUseCaseCallback<DataType> {
        void onSuccess(final DataType successResponse);
        void onFailure();
    }

    //region Private entities. UseCase state.
    private RequestParameterType requestParameters;
    private IUseCaseCallback<ResponseType> callback;
    //endregion

    //region Абстрактная секция. Должна быть переопределена каждым UseCase'ом
    // — это то, что будет выполняться в вызове метода run().
    protected abstract void execute(final RequestParameterType parameters);
    //endregion

    //region Public интерфейс для взаимодействия.
    public void run() {
        execute(requestParameters);
    }
    //endregion

    //region Getters and Setters для приватных полей.
    public RequestParameterType getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(final RequestParameterType requestParameters) {
        this.requestParameters = requestParameters;
    }

    public IUseCaseCallback<ResponseType> getCallback() {
        return callback;
    }

    public void setCallback(final IUseCaseCallback<ResponseType> callback) {
        this.callback = callback;
    }
    //endregion
}
