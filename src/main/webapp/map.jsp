<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.wosai.sqb.storemap.*" %>
<%@ page import="java.util.*" %>
<%@ page import="jxl.*"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=WhOpEKPAkkIEBgFd1pGi97xO"></script>
    <!--加载鼠标绘制工具-->
    <script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
    <link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
    <script type="text/javascript" src="jquery-1.8.3.js"></script>

    <%

          double _lng = 121.48;
          double _lat = 31.22;

          String lat = request.getParameter("lat");
          String lng = request.getParameter("lng");

          if(lat!=null){
             _lat = Double.parseDouble(lat);
             _lng = Double.parseDouble(lng);
          }

           // 参数

           String city = request.getParameter("city");
           String type = request.getParameter("type");
           String district=request.getParameter("district"); //行政区
           String radius=request.getParameter("radius");
           city = "".equals(city)?null : city;
           type = "".equals(type)?null : type;
           radius="".equals(radius)?null:radius;
           district = "".equals(district)?null : district;
           double _radius=0;

            if("SH".equals(city)){city="上海";}
            if("GZ".equals(city)){city="广州";}
            if("BJ".equals(city)){city="北京";}
            if("SZ".equals(city)){city="深圳";}



           if(radius!=null){
           _radius=Double.parseDouble(radius);
           }


       %>
</head>
<script type="text/javascript">
$('#searchRadius').val()
</script>
<body onload="test();">
<%
if("上海".equals(city)){
 if(radius==null){
  _lat=31.22;
 _lng=121.48;
 }

%>
城市: <select id="city">
        <option value="上海">上海</option>
      </select>
行政区:<select id="district">
       <option value="虹口区">虹口区</option>
       <option value="普陀区">普陀区</option>
       <option value="浦东新区">浦东新区</option>
       <option value="长宁区">长宁区</option>
       <option value="宝山区">宝山区</option>
       <option value="闵行区">闵行区</option>
       <option value="松江区">松江区</option>
       <option value="奉贤区">奉贤区</option>
       <option value="嘉定区">嘉定区</option>
       <option value="徐汇区">徐汇区</option>
       <option value="青浦区">青浦区</option>
       <option value="闸北区">闸北区</option>
       <option value="杨浦区">杨浦区</option>
 </select>

 <% }else if("北京".equals(city)){
  if(radius==null){
  _lat=39.9;
 _lng=116.3;
}
  %>
         城市: <select id="city">
               <option value="北京">北京</option>
               </select>
         行政区:<select id="district">
               <option value="丰台区">丰台区</option>
               <option value="海淀区">海淀区</option>
               <option value="通州区">通州区</option>
               <option value="朝阳区">朝阳区</option>
               <option value="西城区">西城区</option>
               <option value="东城区">东城区</option>
               <option value="昌平区">昌平区</option>
               <option value="大兴区">大兴区</option>
               <option value="石景山区">石景山区</option>
               <option value="石景山区">石景山区</option>
          </select>
 <%}else if("深圳".equals(city)){
 if(radius==null){
  _lat=22.55;
  _lng=114.07;
 }


 %>
                  城市: <select id="city">
                        <option value="深圳">深圳</option>
                        </select>
                  行政区:<select id="district">
                         <option value="龙岗区">龙岗区</option>
                         <option value="宝安区">宝安区</option>
                         <option value="南山区">南山区</option>
                         <option value="福田区">福田区</option>
                         <option value="市辖区">市辖区</option>
                         <option value="罗湖区">罗湖区</option>
                   </select>

 <%}else if("广州".equals(city)){
 if(radius==null){
  _lat=23.20;
  _lng=113.30;
 }

 %>
          城市: <select id="city">
          <option value="广州">广州</option>
          </select>
          行政区:<select id="district">
          <option value="越秀区">越秀区</option>
          <option value="白云区">白云区</option>
          <option value="天河区">天河区</option>
          <option value="番禺区">番禺区</option>
          <option value="花都区">花都区</option>
          <option value="从化市">从化市</option>
          <option value="东山区">东山区</option>
          <option value="增城市">增城市</option>
          <option value="海珠区">海珠区</option>
          <option value="荔湾区">荔湾区</option>
          <option value="芳村区">芳村区</option>
          <option value="市辖区">市辖区</option>
          <option value="黄埔区">黄埔区</option>
          </select>
<%}%>


类别: <select id="type">
        <option value="默认">默认</option>
        <option value="低频商户">低频商户</option>
        <option value="中频商户">中频商户</option>
        <option value="高频商户">高频商户</option>
        <option value="僵尸商户">僵尸商户</option>

      </select>
半径: <select id="searchRadius">
        <option value="100">100米</option>
        <option value="500">500米</option>
        <option value="1000">1千米</option>
        <option value="2000">2千米</option>
        <option value="3000">3千米</option>
        <option value="4000">4千米</option>
        <option value="5000">5千米</option>
        <option value="6000">6千米</option>
        <option value="7000">7千米</option>
        <option value="8000">8千米</option>
        <option value="9000">9千米</option>
        <option value="10000">10千米</option>
      </select>

<div id="storemap" style="width: 95%; height: 560px; border: solid 1px #333;"></div>
<%@ include file="map_include.jsp" %>

<script type="text/javascript">
   var infoWindows = {};
<%
     List<StoreBean> rs = new ArrayList<StoreBean>();
     ReadMessage rm=new ReadMessage();
        if(radius!=null){
        rs=  rm.getAroundStore(_lat,_lng,_radius);
        }else{
        rs=  rm.getStoreByCityAndDistrictAndType(city,district,type);
        }




     for(int i=0;i<rs.size();i++){
       StoreBean bean = rs.get(i);

       if((int)bean.getLatitude()!=0&&(int)bean.getLongitude()!=0){
         String urlIcon="smile.png";
         if(bean.getType().equals("僵尸商户")) urlIcon="quiet.png";
         if(bean.getType().equals("低频商户")) urlIcon="low.png";
         if(bean.getType().equals("高频商户")) urlIcon="devotion.png";
%>
         var store = {};
         var urlIcon='<%=urlIcon%>'
         store['id'] = '<%=bean.getId()%>';
         store['storeName']='<%=bean.getStoreName()%>';
         store['userName']='<%=bean.getUserName()%>';
         store['tel']='<%=bean.getTel()%>';
         store['city']='<%=bean.getCity()%>';
         store['address']='<%=bean.getAddress()%>';
         store['longitude']='<%=bean.getLongitude()%>';
         store['latitude']='<%=bean.getLatitude()%>';
         store['district']='<%=bean.getDistrict()%>';
         store['type']='<%=bean.getType()%>';

         infoWindows[store['id']] = createInfoWindow(store);

         var point = new BMap.Point(store['longitude'], store['latitude']);

         createImarker(point, store,urlIcon);

<%
}
 }
%>
</script>

<input name="show2" type="button" onClick="show('div2');" value="显示商户"></input>  总商户数:<%=rs.size()%>
<div id="div2" style="width: 100%; font-size:14px; border: solid 1px #333;display: none;position:absolute; top:620;left:10;">
查询到的商店<br>
<table id="table2" width="100%" border="1" cellpace="0"cellspacing="0">
    <tr>
        <td>商户编号</td><td>商店名称</td>
        <td>联系人</td><td>联系方式</td>
        <td>地址</td><td>类型</td><td>行政区</td><td>经度</td>
    </tr>
<%
 for(int i=0;i<rs.size();i++){
    StoreBean sb=rs.get(i);
    if((int)sb.getLongitude()!=0&&(int)sb.getLatitude()!=0){
%>
    <tr>
    <td><%=sb.getId()%></td><td><%=sb.getStoreName()%></td>
    <td><%=sb.getUserName()%></td><td><%=sb.getTel()%></td>
    <td><%=sb.getAddress()%></td><td><%=sb.getType()%></td>
    <td><%=sb.getDistrict()%></td><td><%=sb.getLongitude()%></td>

    </tr>
<%
    }
 }
%>
</div>

<script type="text/javascript">

function show(id){
var sbtitle=document.getElementById(id);
if(sbtitle.style.display=='block'){
sbtitle.style.display='none';
}else{
sbtitle.style.display='block';
}
}
</script>

<script type="text/javascript">
   $(function(){
      $('#type').val('<%=type%>');

      $('#city').val('<%=city%>');

      $('#district').val('<%=district%>');

      $('#searchRadius').val('<%=radius%>');

      $('#type').change(function(){ //类型改变,按城市和类型查

      location.href="map.jsp?city="+$('#city').val()+"&district="+$('#district').val() +"&type="+$('#type').val();
      });

      $('#district').change(function(){  //行政区改变,按城市和行政区查

      location.href="map.jsp?city="+$('#city').val()+"&district="+$('#district').val() +"&type="+$('#type').val();
      });
   });

function test()
{
 var result = "<%=session.getAttribute("username")%>";
 console.log(result);
 if(result=="null"||result==null){
 alert("你还没有登录");
  location.href="hello.jsp";
 }
};


</script>
</body>
</html>