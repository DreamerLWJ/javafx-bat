package org.example.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 作为{@link javafx.scene.control.ListView}显示的子项Cell的基类，
 * 既可以简单展示文本，也可以展示更复杂的布局。
 * 在打算实现自定义子项视图时，子类必须作为FXML布局的视图控制器，
 * 并在{@link #bindData(T)}里为绑定的控件更换上面显示的数据。
 * @param <T> 展示在表格里的实体类类型
 */
public abstract class BaseCell<T> extends ListCell<T> implements Callback<Class<?>, Object> {

    private final URL fxmlURL;

    /**
     * 构造表上某一列的视图
     * @param fxmlURL 自定义控件的FXML资源定位符，如果为空，则简单展示文本
     */
    public BaseCell(URL fxmlURL) {
        this.fxmlURL = fxmlURL;
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            // 如果这是一个空行
            setText(null);
            setGraphic(null);
        } else if (getGraphic() == null) {
            // 如果尚未创建需要显示的节点
            if (fxmlURL == null) {
                // 不设置自定义的节点，使用默认的显示方式显示信息
                setText(item.toString());
            } else {
                // 创建节点，并绑定数据
                try {
                    setGraphic(FXMLLoader.load(fxmlURL, null, null, this,
                            Charset.forName(FXMLLoader.DEFAULT_CHARSET_NAME)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bindData(item);
        } else {
            // 已经创建过节点，更新数据显示
            bindData(item);
        }

    }

    /**
     * 子类需要实现的用于绑定数据的方法。除了空行之外，该方法都会得到调用，可以使用这个方法覆盖默认行为。
     * 在新建Cell或者滚动{@link javafx.scene.control.ListView}时，这个方法会被反复调用以更新数据，
     * 但应用程序只会创建有限的Cell，此后持续复用它们，因此必须谨慎地处理Cell重用带来的一些问题，
     * 例如控件中会残留着已经消失在视野中的某行的信息。
     * @param item 当前位置需要展示的类，不会为空
     */
    public abstract void bindData(T item);

    @Override
    public Object call(Class<?> param) {
        return this;
    }
}
