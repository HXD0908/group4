package com.example.mymvp;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.mymvp.utils.clusterutil.clustering.ClusterItem;
import com.example.mymvp.utils.clusterutil.clustering.ClusterManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    private MapView mapView = null;
    private RadioButton btnOrdinary;
    private RadioButton btnMoon;
    private RadioButton btnHeating;
    private RadioButton btnTime;
    private BaiduMap map;
    private LocationClient mLocationClient;
    private ClusterManager mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        // 获取地图控件引用
        mapView = (MapView) findViewById(R.id.mapView);
        // TODO 获取百度地图
        map = mapView.getMap();
        //设置普通地图
        map.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        initMap();

        // 定位
        initLocation();

        initCluster();
    }

    private void initCluster() {
        //初始化点聚合管理类
        mClusterManager = new ClusterManager<MyItem>(this, map);

        // 添加Marker点
        LatLng llA = new LatLng(39.963175, 116.400244);
        LatLng llB = new LatLng(39.942821, 116.369199);
        List<MyItem> items = new ArrayList<MyItem>();
        items.add(new MyItem(llA));
        items.add(new MyItem(llB));
        mClusterManager.addItems(items);


        map.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng pLatLng) {
                // 添加Marker点
                List<MyItem> items = new ArrayList<MyItem>();
                items.add(new MyItem(pLatLng));
                mClusterManager.addItems(items);
            }

            @Override
            public void onMapPoiClick(MapPoi pMapPoi) {

            }
        });
    }

    //ClusterItem接口的实现类
    public class MyItem implements ClusterItem {
        LatLng mPosition;

        public MyItem(LatLng position) {
            mPosition = position;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        @Override
        public BitmapDescriptor getBitmapDescriptor() {
            return BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_gcoding);
        }
    }

    private void initMap() {

        btnOrdinary = (RadioButton) findViewById(R.id.btn_ordinary);
        btnMoon = (RadioButton) findViewById(R.id.btn_moon);
        btnHeating = (RadioButton) findViewById(R.id.btn_heating);
        btnTime = (RadioButton) findViewById(R.id.btn_time);

        btnOrdinary.setOnClickListener(this);
        btnMoon.setOnClickListener(this);
        btnHeating.setOnClickListener(this);
        btnTime.setOnClickListener(this);


    }

    private void initLocation() {
        // 开启地图的定位图层
        map.setMyLocationEnabled(true);

        //定位初始化
        mLocationClient = new LocationClient(this);

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        mLocationClient.start();
        map.setMyLocationEnabled(false);
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ordinary:
                map.setBaiduHeatMapEnabled(false);
                // TODO 普通地图 map是地图控制器对象
                map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                map.setTrafficEnabled(false);
                break;
            case R.id.btn_moon:
                // TODO 卫星地图
                map.setBaiduHeatMapEnabled(false);
                map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                map.setTrafficEnabled(false);
                break;
            case R.id.btn_heating:
                // TODO 热力图
                map.setBaiduHeatMapEnabled(true);
                map.setTrafficEnabled(false);
                break;
            case R.id.btn_time:
                // TODO 实时路况图
                map.setBaiduHeatMapEnabled(false);
                map.setTrafficEnabled(true);
                map.setTrafficEnabled(true);
                break;
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            map.setMyLocationData(locData);
        }
    }

}