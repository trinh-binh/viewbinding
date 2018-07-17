package windy.viewbinding.binder;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import windy.viewbinding.annotation.BindView;
import windy.viewbinding.annotation.OnClick;

/**
 * @trinh.binh on 17/07/2018.
 */
public class ViewBinder {
    private static ViewBinder viewBinder;

    private ViewBinder() {

    }

    public static ViewBinder getBinder() {
        if (viewBinder == null) {
            synchronized (ViewBinder.class) {
                if (viewBinder == null) {
                    viewBinder = new ViewBinder();
                }
            }
        }
        return viewBinder;
    }

    public void bindView(final Activity activity) {
        try {
            Field[] fields = activity.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(BindView.class)) {
                    Log.d("windy.f", " field type : " + field.getType());
                    BindView bindView = field.getAnnotation(BindView.class);
                    if (bindView == null) return;
                    int viewId = bindView.value();
                    View view = activity.findViewById(viewId);
                    field.set(activity, view);
                }
            }
            Method[] methods = activity.getClass().getDeclaredMethods();
            for (final Method method : methods) {
                if (method.isAnnotationPresent(OnClick.class)) {
                    OnClick bindView = method.getAnnotation(OnClick.class);
                    if (bindView == null) return;
                    int viewId = bindView.value();
                    View view = activity.findViewById(viewId);
                    if (view instanceof Button) {
                        Log.d("windy.f", " Click on button: " + viewId);
                    }
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                method.invoke(activity, null);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
