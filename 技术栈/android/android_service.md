## android 自带的服务

### wifi

在Android开发中，可以通过`WifiManager`类来获取WiFi信息。以下是一些常用的方法来获取WiFi信息：

1. **获取WiFi连接状态**：
   - `WIFI_STATE_DISABLED`：WiFi已经关闭。
   - `WIFI_STATE_DISABLING`：WiFi正在关闭。
   - `WIFI_STATE_ENABLED`：WiFi已经打开。
   - `WIFI_STATE_ENABLING`：WiFi正在打开。
   - `WIFI_STATE_UNKNOWN`：WiFi状态未知。

2. **获取已连接的WiFi的详细信息**：
   - `getSSID()`：获取WiFi的SSID。
   - `getBSSID()`：获取WiFi的BSSID。
   - `getIpAddress()`：获取WiFi的IP地址。
   - `getMacAddress()`：获取WiFi的MAC地址。
   - `getLinkSpeed()`：获取WiFi的连接速度（Mbps）。
   - `getNetworkId()`：获取WiFi的网络ID。
   - `getRssi()`：获取WiFi的信号强度（dBm）。
   - `getSupplicantState()`：获取WiFi的连接状态。
   - `getWifiConfiguration()`：获取WiFi的配置信息。
   - `getHiddenSSID()`：获取WiFi是否隐藏SSID。
   - `getFrequency()`：获取WiFi的频率（MHz）。

3. **获取周围WiFi热点**：
   - 使用`getScanResults()`方法来获得周围的wifi信息，其返回的是一组`ScanResult`对象，存放在list当中，每个`ScanResult`对象封装了一个wifi热点相关信息。

4. **权限设置**：
   - 在`AndroidManifest.xml`文件中添加相应的权限：
     ```xml
     <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
     <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
     ```

5. **示例代码**：
   ```java
   WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
   WifiInfo wifiInfo = wifiManager.getConnectionInfo();
   String ssid = wifiInfo.getSSID();
   String bssid = wifiInfo.getBSSID();
   int ipAddress = wifiInfo.getIpAddress();
   String macAddress = wifiInfo.getMacAddress();
   int linkSpeed = wifiInfo.getLinkSpeed();
   int networkId = wifiInfo.getNetworkId();
   int rssi = wifiInfo.getRssi();
   SupplicantState supplicantState = wifiInfo.getSupplicantState();
   WifiConfiguration wifiConfiguration = wifiInfo.getWifiConfiguration();
   boolean hiddenSSID = wifiInfo.getHiddenSSID();
   int frequency = wifiInfo.getFrequency();
   ```

6. **获取已保存的WiFi网络**：
   - 使用`WifiManager`的`getConfiguredNetworks()`方法来获取已保存的WiFi网络列表。

7. **扫描周围的WiFi**：
   - 开始扫描周围的WiFi热点：
     ```java
     boolean b = wifiManager.startScan();
     List<ScanResult> list = wifiManager.getScanResults();
     for (ScanResult scanResult : list) {
         String ssid = scanResult.SSID;
         String bssid = scanResult.BSSID;
         int level = scanResult.level;
     }
     ```

以上信息可以帮助你在Android应用中获取和处理WiFi相关的信息。
