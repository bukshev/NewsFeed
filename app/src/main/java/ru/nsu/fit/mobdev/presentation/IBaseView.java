package ru.nsu.fit.mobdev.presentation;

public interface IBaseView<T extends IBasePresenter> {

    /**
     * В Vanilla MVP связь "View к Presenter" 1 к 1 — View может узнать о Presenter'е через данный метод.
     * Presenter может узнать о View, к примеру, на уровне инициализации самого класса.
     */
    void linkPresenter(final T presenter);
}
