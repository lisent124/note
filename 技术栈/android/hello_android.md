# Hello Android

## 简介

Android是一个基于Linux内核的开源操作系统，主要被设计用于触屏移动设备，如智能手机和平板电脑。

它由Android Inc.（一家成立于2003年的公司）最初开发，后来在2005年被Google收购。

Android系统因其开放性和可定制性而广受欢迎，它允许设备制造商和开发者自由地修改和分发系统。

## 历代版本

Android操作系统自2008年首次发布以来，经历了多个版本的更新，每个版本都带来了新特性和改进。

1. Android 1.0 – 1.1 (无甜点代号) - 2008年发布
2. Android 1.5 Cupcake - 2009年发布
3. Android 1.6 Donut - 2009年发布
4. Android 2.0 – 2.1 Eclair - 2009年发布
5. Android 2.2 Froyo - 2010年发布
6. Android 2.3 Gingerbread - 2010年发布
7. Android 3.0 – 3.2 Honeycomb - 2011年发布，主要为平板设计
8. Android 4.0 Ice Cream Sandwich - 2011年发布
9. Android 4.1 – 4.3 Jelly Bean - 2012年发布
10. Android 4.4 KitKat - 2013年发布
11. Android 5.0 – 5.1 Lollipop - 2014年发布
12. Android 6.0 Marshmallow - 2015年发布
13. Android 7.0 – 7.1 Nougat - 2016年发布
14. Android 8.0 – 8.1 Oreo - 2017年发布
15. Android 9 Pie - 2018年发布
16. Android 10 - 2019年发布，这是第一个没有甜点代号的版本
17. Android 11 - 2020年发布
18. Android 12 - 2021年发布
19. Android 13 - 2022年发布
20. Android 14 - 2023年发布
21. Android 15 (Beta) - 2024年发布，目前处于测试阶段，预计将在2024年晚些时候正式发布。

## 开发工具

在Android开发过程中，会用到许多工具来提高开发效率和应用性能。以下是一些常用的工具：

1. **Android Studio**：这是Google官方推荐的Android开发IDE，基于IntelliJ IDEA，提供了丰富的插件和工具，专门针对Android开发进行了优化。它支持代码编写、调试、运行和测试等整个开发周期。
2. **Gradle**：这是一个自动化构建工具，用于编译和打包Android应用。它在Android Studio中默认集成，负责依赖管理和多项目构建。
3. **Git**：版本控制系统，用于代码的版本控制和团队协作。GitHub、GitLab和Bitbucket是常用的远程仓库服务。
4. **SDK Tools**：包括Android SDK Platform-tools和Android SDK Build-tools，用于提供开发Android应用所需的各种工具和库。
5. **AVD Tools**: 创建和管理系统上模拟的 Android 设备。
6. **adb Tools**: 多功能命令行工具，它允许开发者与连接的 Android 设备或运行中的 Android 模拟器进行通信。
7. **JUnit**：用于编写和运行测试用例，确保应用的稳定性和可靠性。

### Gradle

AS 在构建项目时候 会根据配置文件 **gradle-wrapper.properties** 中下载源配置自动下载所需要的gradle版本到 *\user\\.gradle\wrapper\dist\ * 路径下。

在Gradle中，有几个关键的文件用于配置和管理项目的构建过程。以下是这些文件的详细介绍：
1. **`build.gradle` 文件**：
   - 这是Gradle构建配置的主要文件，通常位于项目的根目录以及各个子模块的目录中。
   - 它使用Groovy或Kotlin DSL来定义项目设置，包括依赖、插件、任务和其他构建逻辑。
   - 可以为不同的项目级别（如项目和子项目）定义多个`build.gradle`文件。
2. **`settings.gradle` 文件**：
   - 这个文件位于项目的根目录下，用于配置项目设置，特别是对于多项目构建。
   - 它定义了项目的层次结构，包括根项目和子项目。
   - 在此文件中，你可以指定哪些目录应该被视为独立的子项目，以及哪些插件应该被应用到整个构建中。
   - `settings.gradle`文件还负责配置构建缓存和一些全局设置。
3. **`gradle.properties` 文件**：
   - 这个文件用于定义项目级的属性，这些属性可以在`build.gradle`文件中被引用。
   - 它通常用于指定项目的版本号、应用ID等配置信息。
   - 也可以在此处配置一些Gradle的全局属性，如JVM选项。
4. **`local.properties` 文件**：
   - 这个文件通常位于项目的根目录下，用于存储本地环境特定的配置，如Android SDK的位置。
   - 它对于Android开发尤为重要，因为Android Gradle插件使用这些属性来定位SDK和JDK。
   - 这些属性通常在首次运行Android Studio时自动生成。
5. **`gradlew` 和 `gradlew.bat` 文件**：
   - 这些是Gradle Wrapper的脚本文件，分别用于Unix系统（如Linux和macOS）和Windows系统。
   - 它们用于在没有预先安装Gradle的情况下运行Gradle构建，因为它们会下载并使用项目`gradle-wrapper.properties`文件中指定的Gradle版本。
6. **`gradle-wrapper.properties` 文件**：
   - 这个文件位于项目根目录下的`gradle/wrapper`目录中，用于指定Gradle Wrapper应该使用的Gradle发行版。
   - 它定义了Gradle的版本和下载URL，确保所有开发者和构建系统使用相同的Gradle版本。
   - 
这些文件共同构成了Gradle构建系统的基础，允许你精确地控制项目的构建、测试和部署过程。

## 文件结构

一个典型的Android Studio项目具有以下文件和目录结构：

1. **项目根目录**：
   - 这是整个项目的顶级目录，通常与项目名称相同。
2. **app/**：
   - 这是应用程序模块的根目录，如果你有多个模块，每个模块都会有一个类似的目录。
3. **manifests/**：
   - 包含`AndroidManifest.xml`文件，这是Android应用的全局宣言，定义了应用的包名、所需权限、组件（如活动、服务、广播接收器和内容提供者）等。
4. **java/** 或 **kotlin/**：
   - 包含项目的主要源代码，通常按包结构组织。这是存放你的活动（Activities）、碎片（Fragments）、服务（Services）、适配器（Adapters）、工具类和其他Java或Kotlin类的地方。
5. **res/**：
   - 资源目录，包含用户界面元素，如布局（layouts）、菜单（menus）、颜色（colors）、字符串（strings）、样式（styles）、图片（drawables）、动画（animations）等。
6. **layout/**：
   - 资源子目录，包含XML布局文件，定义了应用的用户界面。
7. **mipmap/**：
   - 资源子目录，包含不同密度的图标文件，用于应用的启动图标。
8. **values/**：
   - 资源子目录，包含字符串、样式、颜色定义等。
9. **assets/**：
   - 包含应用的辅助资源文件，如自定义字体、原始数据文件等。
10. **libs/** 或 **libs-release/**：
    - 包含项目依赖的外部库，通常是.jar或.aar文件。
11. **local.properties**：
    - 包含本地开发环境的配置，如SDK路径。
12. **src/**：
    - 源代码目录，通常包含`main`、`androidTest`和`test`子目录，分别用于存放应用代码、Android特定测试代码和单元测试代码。

以下是一个文件树的例子
一个普通的Android项目在Android Studio中的文件树结构大致如下：

```
YourProjectName/
├── .gitignore              // Git版本控制忽略文件配置
├── app/
│   ├── manifests/
│   │   └── AndroidManifest.xml  // 应用的全局宣言文件
│   ├── java/
│   │   └── com.example.yourproject/
│   │       ├── MainActivity.java  // 示例活动
│   │       ├── ...               // 其他类和包
│   ├── kotlin/
│   │   └── com.example.yourproject/
│   │       ├── MainActivity.kt   // 示例活动（Kotlin版本）
│   │       ├── ...               // 其他类和包
│   ├── res/
│   │   ├── anim/                // 动画资源
│   │   ├── color/               // 颜色资源
│   │   ├── drawable/            // 图片资源
│   │   ├── layout/              // 布局资源
│   │   ├── menu/                // 菜单资源
│   │   ├── mipmap/              // 不同密度的图标资源
│   │   ├── raw/                 // 原始文件资源
│   │   ├── values/              // 字符串、样式、主题等资源
│   │   │   ├── strings.xml      // 字符串资源
│   │   │   ├── styles.xml       // 样式资源
│   │   │   └── ...              // 其他资源文件
│   │   └── ...                  // 其他资源目录
│   ├── assets/                // 存放未编译的资源文件
│   ├── build.gradle            // 模块级别的构建脚本
│   └── proguard-rules.pro      // ProGuard配置文件
├── build.gradle               // 项目级别的构建脚本
├── gradle/                    // Gradle相关配置和脚本
│   ├── wrapper/                // Gradle Wrapper配置
│   │   └── gradle-wrapper.properties
│   └── ...                    // 其他Gradle配置文件
├── gradlew                    // Unix系统上的Gradle Wrapper执行脚本
├── gradlew.bat                // Windows系统上的Gradle Wrapper执行脚本
├── local.properties           // 本地环境配置文件，如SDK路径
├── settings.gradle            // 项目设置脚本，用于配置模块信息
└── ...                        // 其他项目配置文件
```

## 常用组件

在Android应用开发中，组件是构成应用的基本构建块。以下是Android中的常用组件及其简要说明：

1. **活动（Activity）**：
   - 活动是应用中的一个屏幕，用户可以与之交互。每个活动都包含一个用户界面，通常由一个或多个布局文件定义。
   - 活动是用户与应用交互的窗口，可以响应用户的操作，如点击和滑动。
2. **碎片（Fragment）**：
   - 碎片是活动的一部分，它有自己的生命周期和输入事件。
   - 碎片可以动态地添加到活动中，使得活动更加灵活和可重用。
   - 碎片可以用来构建复杂的用户界面，也可以在不同的活动中复用。
3. **服务（Service）**：
   - 服务是用于执行后台操作的组件，它不提供用户界面。
   - 服务可以在前台运行，也可以在后台运行，并且可以在应用的其他组件不运行时继续运行。
4. **广播接收器（Broadcast Receiver）**：
   - 广播接收器用于监听和响应系统或应用发送的广播消息。
   - 它可以响应各种事件，如电池低、屏幕关闭、接收到短信等。
5. **内容提供者（Content Provider）**：
   - 内容提供者用于管理应用中的数据，并提供一种访问数据的方式，通常是结构化数据。
   - 它允许不同应用之间共享数据，如联系人信息、日历事件等。
6. **适配器（Adapter）**：
   - 适配器用于将数据集合映射到视图集合，常用于列表（如ListView）和网格（如GridView）。
   - 适配器负责将数据对象填充到视图中，并处理视图的点击事件。
7. **意图（Intent）**：
   - 意图是Android中的一种消息传递对象，用于请求操作。
   - 它可以用来启动活动、服务，或者传递数据和请求给广播接收器。
8. **视图（View）**：
   - 视图是用户界面的基本构建块，如按钮、文本框、图像视图等。
   - 开发者可以创建自定义视图，或者使用Android SDK提供的现成视图组件。
9. **布局（Layout）**：
   - 布局是定义用户界面结构的XML文件，它指定了视图的排列方式。
   - 常见的布局包括线性布局（LinearLayout）、相对布局（RelativeLayout）、帧布局（FrameLayout）和约束布局（ConstraintLayout）。
10. **资源（Resources）**：
    - 资源是应用中的非代码资产，如字符串、颜色、图片和动画。
    - 资源可以被多个组件共享，并且支持不同的语言和屏幕尺寸。
11. **数据绑定（Data Binding）**：
    - 数据绑定是一种将布局中的视图与应用中的数据源直接绑定的技术。
    - 它减少了样板代码，使得UI组件能够自动更新以反映数据的变化。
12. **通知（Notification）**：
    - 通知用于向用户显示重要的更新和信息，即使应用没有运行。
    - 它们可以包含文本、图标、声音和操作按钮。

这些组件是构建Android应用的基础，通过它们，开发者可以创建功能丰富、用户友好的移动应用。

## 常用控件

在Android开发中，控件（Views）是用户界面的基本构建块。以下是一些常用的Android控件及其描述：

1. **TextView**：
   - 用于显示文本信息。可以设置多种文本样式，如粗体、斜体、下划线等。
2. **Button**：
   - 用户可以点击的按钮。可以设置文本、图标和点击事件监听器。
3. **EditText**：
   - 允许用户输入文本。可以设置输入类型、文本提示和文本监听器。
4. **ImageView**：
   - 用于显示图片。可以加载资源图片或网络图片。
5. **LinearLayout**：
   - 线性布局，可以水平或垂直排列子控件。
6. **RelativeLayout**：
   - 相对布局，通过相对于父布局或其他控件的位置来定位子控件。
7. **FrameLayout**：
   - 框架布局，用于叠加多个子控件。子控件会按照在XML文件中的顺序堆叠显示。
8. **ConstraintLayout**：
   - 约束布局，提供了更灵活的布局方式，可以在父布局中定义控件之间的约束关系。
9. **ListView**：
   - 列表视图，用于显示垂直列表项。每个列表项可以有不同的布局。
10. **RecyclerView**：
    - 灵活的列表视图，用于显示大量数据集。支持数据的滚动、添加、删除和动画效果。
11. **GridView**：
    - 网格视图，用于显示项的网格列表。每个项通常显示为网格中的一个单元。
12. **ScrollView**：
    - 滚动视图，可以包含比屏幕更大的内容，用户可以滚动查看。
13. **HorizontalScrollView**：
    - 水平滚动视图，提供水平方向上的滚动功能。
14. **SeekBar**：
    - 滑块，用户可以通过拖动滑块选择一个值。
15. **CheckBox**：
    - 复选框，用户可以选择或取消选择。
16. **RadioButton**：
    - 单选按钮，用于在一组选项中选择一个。
17. **ToggleButton**：
    - 切换按钮，表示开关状态。
18. **Switch**：
    - 开关，用户可以打开或关闭设置。
19. **ProgressBar**：
    - 进度条，用于显示操作的进度。

这些控件是Android UI开发中的基础，通过组合使用这些控件，可以创建复杂的用户界面。开发者可以根据需要选择适当的控件来实现应用的特定功能和设计。

### 基本属性

Android控件的基本属性用于定义控件的外观和行为。以下是一些最常见的基本属性：

1. **id**：
   - 为控件提供一个唯一标识符，用于在代码中引用控件。
2. **layout_width** 和 **layout_height**：
   - 定义控件的宽度和高度。可以是固定的像素值、填充父布局（match_parent）、包裹内容（wrap_content）或者百分比（百分比布局属性）。
3. **android:layout_marginLeft**, **android:layout_marginTop**, **android:layout_marginRight**, **android:layout_marginBottom**：
   - 分别定义控件左、上、右、下的外边距。
4. **android:paddingLeft**, **android:paddingTop**, **android:paddingRight**, **android:paddingBottom**：
   - 分别定义控件内容左、上、右、下的内边距。
5. **android:text**：
   - 对于`TextView`控件，设置要显示的文本。
6. **android:hint**：
   - 对于`EditText`控件，设置当没有输入时显示的提示文本。
7. **android:src**：
   - 对于`ImageView`控件，设置要显示的图片资源。
8. **android:background**：
   - 设置控件的背景颜色或背景资源。
9. **android:visibility**：
   - 控制控件的可见性，可以是`visible`、`invisible`或`gone`。
10. **android:enabled**：
    - 启用或禁用控件，如果设置为`false`，控件将不会响应用户交互。
11. **android:focusable** 和 **android:clickable**：
    - 控制控件是否可以获得焦点或点击。
12. **android:gravity**：
    - 设置文本或视图内容的对齐方式，如`center`、`left`、`right`等。
13. **android:textSize**, **android:textColor**, **android:textStyle**：
    - 分别设置文本的大小、颜色和样式（如粗体、斜体）。
14. **android:inputType**：
    - 对于`EditText`控件，定义键盘类型和输入行为。
15. **android:ems**：
    - 对于`EditText`控件，定义控件的宽度以“em”为单位。
16. **android:lines**：
    - 对于`TextView`控件，定义文本行数。
17. **android:maxLines** 和 **android:minLines**：
    - 对于`TextView`控件，定义最大和最小行数。
18. **android:scrollbars**：
    - 对于需要滚动的控件，定义滚动条的显示方式。
19. **android:drawableLeft**, **android:drawableTop**, **android:drawableRight**, **android:drawableBottom**：
    - 分别在`TextView`控件的左、上、右、下添加图标或图片。
20. **android:onClick**：
    - 定义点击事件的处理方法。

这些属性可以在XML布局文件中直接设置，也可以在Java或Kotlin代码中动态设置。通过组合这些基本属性，可以创建出丰富多样的UI界面。

## 发布

### 确认信息
在app/build.gradle文件中，确保你已经正确设置了版本名和版本码。
```gradle
android {
    defaultConfig {
        applicationId "com.example.myapp"
        minSdkVersion 21
        targetSdkVersion 33
        versionCode 1 // 唯一的版本号
        versionName "1.0" // 版本名称
}
```
### 生成密钥库
可以使用Android Studio的Generate Signed Bundle / APK向导来生成。

### 打包
在build.gradle中添加，指定生成的apk文件名

```gradle
android.applicationVariants.all {
    variant ->
        variant.outputs.all {
            // 此处指定生成的apk文件名
            outputFileName = "GenerateAPK_${buildType.name}_v${versionName}.apk"

        }
}

```

在Android Studio 的 Build/Build APK(s) 选项中构建

创建好的apk 会在build/outputs/apk 目录下