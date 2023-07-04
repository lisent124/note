# flex布局
通过对父元素添加dispaly: flex；对子元素控制布局

## 常见父项属性
### flex-direction *设置主轴方向*

#### 属性值 *对子元素影响*

- row *默认 左到右*
- row-reverse *右到左*
- column *上到下*
- column-reverse *下到上*



### 主轴与侧轴

* 主轴由flex-direction来决定，而主轴与侧轴相互垂直

### justify-content *设置主轴上的子元素排列方式*

#### 属性值 

- flex-start *默认 头部开始*
- flex-end *尾部开始*
- center *居中*
- space-around *平分剩余空间到子元素两边*
- space-between *先靠父容器再平均分配空间*
- space-evently *每个子元素相对于容器或者其他子元素空间为剩余空间平均分*

### flex-wrap *设置子元素是否换行*

#### 说明

* flex默认不换行，当空间不够是会压缩子元素空间

#### 属性值

- nowarp *默认 不换行*
- warp *换行*

### align-content *设置侧轴上的子元素的排列方式（多行）*

#### 属性值

- flex-start 
- flex-end
- center
- space-around
- space-between

### align-items *设置侧轴上的子元素排列方式（单行）*

#### 说明 *单行指并没有换行的情况发生*

#### 属性值

- flex-start *默认 从头开始*
- flex-end *从尾部开始*

- center *居中*
- stretch *拉伸侧轴方向上子元素的大小，需要子元素不设置该方向上的大小 *

### flex-flow *复合属性，相当于同时设置了flex-direction 和 flex-wrap*

#### 列子

flex-flow: row wrap;



### 子项常见属性

#### flex *定义子项目分配空间，用flex来表示所占份数 默认0*

#### flex-self  *控制子项自己在侧轴方向的对其方式*

#### order *默认0 数值越小越靠前*



