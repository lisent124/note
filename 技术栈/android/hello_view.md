# View

自定义View是Android开发中一个强大的特性，它允许开发者创建具有独特外观和交互行为的界面元素

## 创建和使用步骤

### 基础步骤
1. 继承view
2. 重写OnDraw
3. 编辑相应事件
4. 在布局文件中引用
5. 在逻辑代码中调用

以下是一个绘制一个 圆形 的例子

```java
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

public class CircleView extends View {
    private Paint paint;
    private boolean isPressed; // 用于记录按钮是否被按下
    private OnTouchListener lisenter;

    public CircleView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true); // 抗锯齿
        paint.setStyle(Paint.Style.FILL); // 填充模式
        paint.setColor(Color.White); // 设置颜色
        isPressed = false; // 初始化时，按钮未被按下
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = Math.min(getWidth(), getHeight()) / 2; // 取宽度和高度中较小的一个作为直径
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // 如果按钮被按下，改变颜色
        if (isPressed) {
            paint.setColor(Color.Blue);
        } else {
            paint.setColor(Color.Red);
        }

        canvas.drawCircle(centerX, centerY, radius, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        listener.onTouch(this,event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 当按下时，改变颜色
                isPressed = true;
                invalidate(); // 重绘视图
                return true;
            case MotionEvent.ACTION_UP:
                // 当手指松开时，恢复颜色
                isPressed = false;
                invalidate(); // 重绘视图
                return true;
            case MotionEvent.ACTION_CANCEL:
                // 当事件被取消时，恢复颜色
                isPressed = false;
                invalidate(); // 重绘视图
                return true;
            default:
                return false;
        }
    }
    @Override
    public void setOnTouchListener(OnTouchListener l){
        this.listener = l;
    }
}
```

## 绘制图形
绘制图像主要通过 `Canvas` 来绘制 `Paint` 和 `Path` 两个类。

`Canvas` 它提供了绘制文本、图形、图像和其他可视化内容的接口。`Canvas` 本身不存储绘制的内容，它只是定义了绘制操作。

`Paint` 定义了绘制文本、形状和位图时的一系列样式和属性。

`Path` 类用于定义和操作图形路径，可以包含多个独立的子路径（或轮廓），这些子路径可以是直线段、二次曲线、三次曲线等。`Path` 类提供了丰富的方法来创建和操作这些路径，从而实现复杂的图形绘制。

注意：当在响应事件中改变了图形样式的时候 需要调用 `invalidate();` 来重绘视图OnDraw()

### Canvas
Canvas的基础功能是绘制，如以下操作。
1. 绘制文本
   - `drawText(String text, float x, float y, Paint paint)`：在指定位置 `(x, y)` 使用 `Paint` 绘制文本。`x` 和 `y` 通常指定文本的起始点，可以是基线或底部左侧。    
2. 绘制图形
   - `drawRect(RectF rect, Paint paint)`：绘制一个矩形。
   - `drawRoundRect(RectF rect, float rx, float ry, Paint paint)`：绘制一个圆角矩形。
   - `drawCircle(float cx, float cy, float radius, Paint paint)`：绘制一个圆形。
   - `drawOval(RectF oval, Paint paint)`：绘制一个椭圆形。
   - `drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)`：绘制一个圆弧。
   - `drawPath(Path path, Paint paint)`：绘制一个 `Path` 对象，`Path` 可以定义复杂的形状和路径。
3. 绘制图像
    - `drawBitmap(Bitmap bitmap, float left, float top, Paint paint)`：在指定位置绘制一个位图。
    - `drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint)`：使用 `Matrix` 变换来绘制位图。
    - `drawBitmapMesh(Bitmap bitmap, int meshWidth, int meshHeight, float[] verts, int vertOffset, int[] colors, int colorOffset, Paint paint)`：将位图分割成网格并绘制，允许对每个网格进行变换。
    - `drawImageBitmap(Bitmap bitmap, RectF dst, Paint paint)`：将位图绘制到一个矩形区域。
4. 绘制其他内容
   - `drawColor(int color)`：用指定颜色填充 `Canvas`。
   - `drawPoint(float x, float y, Paint paint)`：绘制一个点。
   - `drawLine(float startX, float startY, float stopX, float stopY, Paint paint)`：绘制一条线段。
   - `drawLines(float[] pts, Paint paint)`：绘制一系列线段。
   - `drawRects(RectF[] rects, Paint paint)`：绘制多个矩形。

除此以外的其他功能。
1. 变换
- `translate(float dx, float dy)`：平移 `Canvas`，后续绘制的内容都会根据这个平移量进行偏移。
- `rotate(float degrees, float pivotX, float pivotY)`：围绕 `pivotX` 和 `pivotY` 旋转 `Canvas`。
- `scale(float sx, float sy, float pivotX, float pivotY)`：围绕 `pivotX` 和 `pivotY` 缩放 `Canvas`。
- `skew(float kx, float ky)`：对 `Canvas` 进行倾斜变换。
2. 裁剪
- `clipRect(RectF rect)`：设置 `Canvas` 的裁剪区域为 `rect`，只有在这个区域内的绘制操作才会被显示。
- `clipPath(Path path)`：设置 `Canvas` 的裁剪区域为 `path`。

### Paint
其主要属性如下。
1. **Color**：
   - `setColor(int color)`：设置画笔颜色。颜色通常以 ARGB（Alpha, Red, Green, Blue）格式指定。
2. **Alpha**：
   - `setAlpha(int alpha)`：设置画笔的透明度，取值范围从 0（完全透明）到 255（完全不透明）。
3. **Style**：
   - `setStyle(Paint.Style style)`：设置画笔的样式，可以是填充（`Style.FILL`）、描边（`Style.STROKE`）、描边填充（`Style.FILL_AND_STROKE`）。
4. **Stroke Width**：
   - `setStrokeWidth(float width)`：设置描边的宽度。
5. **Cap & Join**：
   - `setStrokeCap(Paint.Cap cap)`：设置描边的端点样式，可以是圆形（`Cap.ROUND`）、方形（`Cap.SQUARE`）或 Butt（`Cap.BUTT`）。
   - `setStrokeJoin(Paint.Join join)`：设置描边的连接点样式，可以是圆形（`Join.ROUND`）、斜角（`Join.BEVEL`）或 Miter（`Join.MITER`）。
6. **Text Rendering**：
   - `setTextSize(float textSize)`：设置文本大小。
   - `setTextScaleX(float scaleX)`：设置文本水平缩放。
   - `setTextSkewX(float skewX)`：设置文本水平倾斜。
   - `setTypeface(Typeface typeface)`：设置文本字体。
   - `setTextLocale(Locale locale)`：设置文本的地区设置，用于支持某些语言的特定字符。
7. **Path Rendering**：
   - `setPathEffect(PathEffect effect)`：设置路径效果，如虚线（`DashPathEffect`）。
8. **Shader**：
   - `setShader(Shader shader)`：设置画笔的着色器，可以是线性渐变（`LinearGradient`）、放射性渐变（`RadialGradient`）、扫描渐变（`SweepGradient`）或位图（`BitmapShader`）。
9. **Anti Alias**：
   - `setAntiAlias(boolean aa)`：设置是否开启抗锯齿。
10. **Dither**：
    - `setDither(boolean dither)`：设置是否开启抖动，用于改善颜色梯度的平滑度。


### Path
一些更复杂的图形可以用到 `Path` 来进行绘制。
1. 主要方法
- `moveTo(float x, float y)`：将路径的当前位置移动到指定的点，不创建线条。
- `lineTo(float x, float y)`：从当前位置绘制一条直线到指定的点。
- `quadTo(float x1, float y1, float x2, float y2)`：绘制一个二次贝塞尔曲线。
- `cubicTo(float x1, float y1, float x2, float y2, float x3, float y3)`：绘制一个三次贝塞尔曲线。
- `arcTo(RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)`：绘制一个圆弧。
- `addCircle(float x, float y, float radius, Path.Direction dir)`：绘制一个圆形。
- `addRect(RectF rect, Path.Direction dir)`：绘制一个矩形。
- `addOval(RectF oval, Path.Direction dir)`：绘制一个椭圆形。
- `close()`：闭合当前子路径，创建一个从当前点回到起始点的线段。
2. 路径操作
- `reset()`：重置路径，清除所有内容，但保留 FillType。
- `rewind()`：重置路径，清除所有内容，但不保留 FillType。
- `offset(float dx, float dy)`：平移路径。
- `transform(Matrix matrix)`：对路径应用矩阵变换。
3. 路径填充和布尔运算
- `setFillType(Path.FillType type)`：设置路径的填充类型，如 `Winding` 或 `EvenOdd`。
- `op(Path path, Path.Op op)`：对两个路径进行布尔运算，如合并、差集、交集等。
4. 其他
`Path` 也可以与 `Canvas` 的其他方法结合使用，如 `save`、`restore`、`translate`、`rotate` 等，来实现更复杂的绘制效果。

## 响应事件

无论是点击事件还是触摸事件 都在onTouchEvent 中进行详细操作。
一般来说通过调用setOnTouchListener 来外部定义响应事件。

处理响应事件主要通过 MotionEvent 来进行详细操作。

### MotionEvent
`MotionEvent` 是 Android 中处理触摸事件的核心类，它封装了用户触摸屏幕时产生的所有相关信息
1. MotionEvent 的构成
`MotionEvent` 包含了以下几类信息：
   - **动作类型**：如 `ACTION_DOWN`（手指按下）、`ACTION_MOVE`（手指移动）、`ACTION_UP`（手指抬起）、`ACTION_CANCEL`（触摸事件被打断）等。
   - **位置信息**：包括 `getX()`、`getY()`（返回相对于当前视图的坐标），`getRawX()`、`getRawY()`（返回相对于屏幕的绝对坐标）。
   - **压力和大小**：`getPressure()`、`getSize()`，分别表示触摸的压力和手指接触区域的大小。
   - **时间**：`getEventTime()`，表示事件发生的时间。
   - **指针信息**：在多点触控中，`MotionEvent` 可以包含多个指针（pointer）的信息。每个指针有一个唯一的 ID，可以通过 `getPointerId(int index)` 获取。
1. MotionEvent 的动作类型
`MotionEvent` 的动作类型（Action）是识别用户操作的关键。常见的动作类型包括：
   - `ACTION_DOWN`：初始按下。
   - `ACTION_MOVE`：手指在屏幕上移动。
   - `ACTION_UP`：手指抬起。
   - `ACTION_CANCEL`：事件被系统取消，如另一个程序截获了事件流。
   - `ACTION_POINTER_DOWN`：第二个或后续的手指按下。
   - `ACTION_POINTER_UP`：非第一个手指抬起。
   - `ACTION_OUTSIDE`：触摸事件在控件外部发生。
   - `ACTION_SCROLL`：非触摸滚动，如鼠标滚轮。
1. MotionEvent 的处理
在 `View` 或 `Activity` 中，可以通过 `onTouchEvent(MotionEvent event)` 方法来处理触摸事件。在这个方法中，你可以根据不同的动作类型来执行不同的操作。
1. MotionEvent 的批处理
为了提高效率，`MotionEvent` 对于 `ACTION_MOVE` 事件可能会进行批处理，即在一个 `MotionEvent` 对象中包含多个移动事件。可以通过 `getHistoricalX(int pointerIndex, int historyPos)` 和 `getHistoricalY(int pointerIndex, int historyPos)` 方法来访问这些事件。
1. MotionEvent 的多点触控
在多点触控场景中，`MotionEvent` 可以包含多个指针的信息。可以通过 `getPointerCount()` 获取指针的数量，然后通过 `findPointerIndex(int pointerId)` 方法来获取特定指针的索引，进而获取该指针的状态。
1. MotionEvent 的坐标系统
`MotionEvent` 提供了两种坐标系统：
- **本地坐标**：使用 `getX()`、`getY()` 获取，这些坐标是相对于当前处理事件的视图的。
- **绝对坐标**：使用 `getRawX()`、`getRawY()` 获取，这些坐标是相对于整个屏幕的。
1. MotionEvent 的创建和回收
`MotionEvent` 对象通常由系统创建并传递给应用，应用处理完毕后应该调用 `recycle()` 方法来回收对象，以避免内存泄漏。

