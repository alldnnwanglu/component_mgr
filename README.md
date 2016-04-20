# component_mgr
use like spring frame work ... simple .&amp; easy IOC

# 使用更加轻量级的代码实现Spring面向组件编程

[ 2016-4-19 ]
1. 解决提交过程中中文注释乱码问题. 使用 <br/>
2. 第一个可测试版本，可以运行, 测试. <br/>
3. 去掉部分参考Spring 中我这里使用不到的代码.<br/>

[ 2016-4-20 ]
1. 查看spring 中提供了那些获得bean 的接口，提供并支持.<br/>
  ------------------------------<br/>
  1. applicationContext 提供 getBeansOfType(Class<?> type)<br/>
  2. applicationContext 提供 getBeansWithAnnotation<br/>
  
  

###使用的时候先注册要遍历的位置 <br/>
etc.  <br/>
1.用 ';' 分割<br/>
// TODO 每个包遍历 <br/>
t.doScan("rodking/util/;rodking/server/");<br/>
// TODO 遍历所有包<br/>
// t.doScan("rodking/");<br/>

2.添加需要加载的主键<br/>
// 添加 Component<br/>
includeFilters.add(new AnnotationTypeFilter(Component.class));<br/>
// 添加 DAO<br/>
includeFilters.add(new AnnotationTypeFilter(DAO.class));<br/>
// 添加 Bean<br/>
includeFilters.add(new AnnotationTypeFilter(Bean.class));<br/>
// 添加ProtoAction<br/>
includeFilters.add(new AnnotationTypeFilter(ProtoAction.class));<br/>

3.初始化容器<br/>
ApplicationContext.getInstance().init();<br/>

4.使用容器<br/>

