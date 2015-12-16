<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("storemap");                             // 创建Map实例
	// 默认地图中心点
	var defaultPoint = new BMap.Point(<%=_lng%>,<%=_lat%>);
	map.centerAndZoom(defaultPoint, 12);                          // 初始化地图，设置中心点坐标和地图级别
	map.addControl(new BMap.NavigationControl());                 // 添加平移缩放控件
	map.addControl(new BMap.ScaleControl());                      // 添加比例尺控件
	map.addControl(new BMap.OverviewMapControl());                // 添加缩略地图控件
	//map.enableScrollWheelZoom();                                  // 启用滚轮放大缩小
	map.addControl(new BMap.MapTypeControl());                    // 添加地图类型控件[地图、卫星、三维]
    defaultCircle =createCircle(defaultPoint, 1000);


	// 默认标注
	var defaultMarker = createtMarker(defaultPoint);
	map.addOverlay(defaultMarker);                   // 添加标注




	/**
	 * 创建标注.
	 */
	function createtMarker(point, drag){
		var marker = new BMap.Marker(point);         // 创建标注
		if(drag == null || drag) {
			marker.enableDragging();                 // 可拖拽
		} else {
			marker.disableDragging();
		}
	 	return marker;
	}

	/**
	 * 创建圆形阴影区域.
	 *
	 * @param point BMap.Point坐标点
	 * @param radius 半径，单位米
	 */
	function createCircle(point, radius){
	 	return new BMap.Circle(point, radius, {fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
	}

	/**
	 * 创建自定义图标
	 */
	function createIcon(urlIcon){
		var widthIcon = 24;
    	var heightIcon = 24;
    	// ,imageOffset: new BMap.Size(0, 120) // 去掉imageOffset，否则可能会不显示
    	return new BMap.Icon(urlIcon,new BMap.Size(widthIcon, heightIcon),{offset: new BMap.Size(0, 0-24*24)});
	}

	function createInfoWindow(store){
         var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
                 '<table><tr><td>商户编号：</td><td>' +store['id']+ '</td></tr>' +
                 '<tr><td>联系人：</td><td>' + store['userName'] + '</td></tr> ' +
                 '<tr><td>联系号码：</td><td>' +store['tel']+ '</td></tr>' +
                 '</table>' + '</div>';
         var opts = {
             width: 350,     // 信息窗口宽度
             // height: 160,     // 信息窗口高度    不设置则自适应高度
             enableMessage: false,
             // 信息窗口标题
             title: '<span style="font-size:14px; color: #006600; font-weight: 900;">' +store['storeName']
             + '</span>'
         };
         return new BMap.InfoWindow(content, opts);
	}

	function createImarker(point, store,urlIcon) {
	     var marker = new BMap.Marker(point, {icon: createIcon(urlIcon)});  // 创建标注

         map.addOverlay(marker);              // 将标注添加到地图中
         var label = new BMap.Label(store['storeName'], {offset:new BMap.Size(20,-10)});
       //  marker.setLabel(label);
         marker.addEventListener("click", function () {
             this.openInfoWindow(infoWindows[store['id']]);
         });

	}


	defaultMarker.addEventListener("dragging",
                    function (e) {
                        defaultCircle.hide();
                        searchMapPoint = e.point;
                        defaultCircle = createCircle(e.point, $('#searchRadius').val());
                        $('#search_longitude').val(e.point.lng);
                        $('#search_latitude').val(e.point.lat);
                        map.addOverlay(defaultCircle);
                        location.href="map.jsp?lat="+e.point.lat+"&lng="+e.point.lng+"&radius="+$('#searchRadius').val()+"&city="+$('#city').val();
                    }
            );

$(function(){



$('#searchRadius').change(function () {
                  defaultCircle.hide();
                  defaultCircle = createCircle(defaultMarker, $('#searchRadius').val());
                  map.addOverlay(defaultCircle);

                  location.href="map.jsp?lat="+defaultMarker.point.lat+"&lng="+defaultMarker.point.lng+"&radius="+$('#searchRadius').val()+"&city="+$('#city').val();

                  });
               });


</script>