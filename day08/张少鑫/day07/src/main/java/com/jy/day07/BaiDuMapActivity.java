package com.jy.day07;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
public class BaiDuMapActivity extends AppCompatActivity implements View.OnClickListener{


    private MapView mMapView = null;

    private BaiduMap map;
    private Button mReli;
    private MapView mBmapView;
    private Button mPutongBtn;
    private Button mWeixing;
    private Button mKongbai;
    private Button mLocationBtn;
    private LocationClient mLocationClient;
    private MapStatus mMapStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        mMapView = (MapView) findViewById (R.id.bmapView);
        Permissionsutil ();  // 动态权限
        initView ();
        map = mMapView.getMap (); //获取地图控件引用
        map.setMyLocationEnabled (true);



        //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LocationClient locationClient = new LocationClient(getApplicationContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("bd09ll");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(true);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(true);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000,1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);
//开始定位
        locationClient.start();
    }

    private void initView() {
        mPutongBtn = (Button) findViewById (R.id.btn_putong);
        mPutongBtn.setOnClickListener (this);
        mWeixing = (Button) findViewById (R.id._weixing);
        mWeixing.setOnClickListener (this);
        mKongbai = (Button) findViewById (R.id._kongbai);
        mKongbai.setOnClickListener (this);
        mReli = (Button) findViewById (R.id._reli);
        mReli.setOnClickListener (this);
        mBmapView = (MapView) findViewById (R.id.bmapView);
        mBmapView.setOnClickListener (this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.btn_putong:
                // TODO 20/12/23
                map.setMapType (BaiduMap.MAP_TYPE_NORMAL);
                map.setTrafficEnabled (true);
                map.setBaiduHeatMapEnabled (false);
                break;

            case R.id._weixing:
                map.setMapType (BaiduMap.MAP_TYPE_SATELLITE);
                map.setBaiduHeatMapEnabled (false);
                break;

            case R.id._kongbai:
                map.setMapType (BaiduMap.MAP_TYPE_NONE);
                break;

            case R.id._reli:
                map.setBaiduHeatMapEnabled (true);
                break;
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder ()
                    .accuracy (location.getRadius ())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction (location.getDirection ()).latitude (location.getLatitude ())
                    .longitude (location.getLongitude ()).build ();
            map.setMyLocationData (locData);
        }
    }

    @Override
    protected void onResume() {
        super.onResume ();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume ();
    }

    @Override
    protected void onPause() {
        super.onPause ();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause ();
    }

    @Override
    protected void onDestroy() {
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy ();
        mLocationClient.stop();
        map.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    // 动态权限
    private void Permissionsutil() {
        PermissionsUtil.requestPermission (this, new PermissionListener () {
                    @Override
                    public void permissionGranted(@NonNull String[] permission) {

                    }

                    @Override
                    public void permissionDenied(@NonNull String[] permission) {

                    }
                }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
                , Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
    }
}



