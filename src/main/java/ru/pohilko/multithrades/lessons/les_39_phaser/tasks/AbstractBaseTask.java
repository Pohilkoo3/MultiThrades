package ru.pohilko.multithrades.lessons.les_39_phaser.tasks;

public abstract class AbstractBaseTask {

    protected final Integer id;
    protected final String TEMPLATE_NAME = "abstract_task_%d ";
    protected String name;
    protected boolean isPerform;

    public AbstractBaseTask(Integer id) {
        this.id = id;
        this.name = TEMPLATE_NAME.formatted(id);
        this.isPerform = false;
    }

    public abstract void perform();

    protected abstract boolean isPerform();
}
