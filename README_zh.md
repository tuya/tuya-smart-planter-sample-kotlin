Tuya Android Smart Planter Sample
========================
[English](README.md) | [中文版](README_zh.md) 

功能概述
------------------------

Tuya Android Smart Planter Sample 提供简单示例用于对智能WiFi植物生长机的远程控制以及数据监测,该植物生长机基于Tuya IoTOS Embeded WiFi &Ble SDK实现。

Tuya Android Smart Planter Sample 已实现以下功能：

- 用户管理模块(注册、登录、重置密码)
- 默认家庭创建
- 设备配网模块(EZ配网模式)
- 设备控制模块(植物生长机开关、风速控制、温湿度监测、土壤湿度、光照等)




|                       超全版植物生长机                       |                       精简版植物生长机                       |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| <img src="https://images.tuyacn.com/app/aiwen/tuya-smart-planter/4e61e17e33f74f59bf484f7caf47a2dd(1).jpg" style="zoom:15%;" /> | <img src="https://images.tuyacn.com/app/aiwen/tuya-smart-planter/a148f86f05764d9aa85780887e14fc00(1).jpg" style="zoom:10%;" /> |
| [超全功能智能植物生长机Demo](https://developer.tuya.com/cn/demo/smart-planter) | [精简版智能植物生长机Demo](https://developer.tuya.com/cn/demo/smart-planter-lite) |



### 相关硬件和嵌入式项目

- 智能WiFi植物生长机Demo的详细介绍文档，请参考涂鸦Demo中心：[https://developer.tuya.com/cn/demo/smart-planter](https://developer.tuya.com/cn/demo/smart-planter)

- Tuya IoTOS Embeded Demo WiFi & BLE Smart-Planter 项目 Github 仓库: [https://github.com/tuya/tuya-iotos-embeded-demo-wifi-ble-smart-planter/blob/master/README_zh.md](https://github.com/tuya/tuya-iotos-embeded-demo-wifi-ble-smart-planter/blob/master/README_zh.md)

- 开发板套件相关详情请参考：[三明治开发套件](https://developer.tuya.com/cn/docs/iot/device-development/tuya-development-board-kit/tuya-sandwich-evaluation-kits/-tuya-sandwich-evaluation-kits?id=K97o0ixytemvr)





开始
------------------------

#### 一、项目配置

1、根据[准备工作](https://developer.tuya.com/zh/docs/app-development/android-app-sdk/preparation?id=Ka7mqlxh7vgi9)文档说明，注册涂鸦开发者账号，并完成应用的创建，将Sample的包名替换成创建时设置的包名。

2、根据[集成SDK](https://developer.tuya.com/zh/docs/app-development/android-app-sdk/integration/integrated?id=Ka69nt96cw0uj)文档说明，集成安全图片以及重新设置AppKey和AppSecret。

#### 二、界面

在完成以上项目配置后，运行程序，App展示各个界面如下：

|                             首页                             |                             注册                             |                             登录                             |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| <img src="https://images.tuyacn.com/app/aiwen/tuya-smart-planter/user_operate_zh.jpg" style="zoom:20%;" /> | <img src="https://images.tuyacn.com/app/aiwen/tuya-smart-planter/user_register_zh.jpg" style="zoom:20%;" />  | <img src="https://images.tuyacn.com/app/aiwen/tuya-smart-planter/user_login_zh.jpg" style="zoom:20%;" />  |
|                           重置密码                           |                             主页                             |                           用户信息                           |
| <img src="https://images.tuyacn.com/app/aiwen/tuya-smart-planter/user_reset_password_zh.jpg" style="zoom:20%;" /> | <img src="https://images.tuyacn.com/app/aiwen/tuya-smart-planter/home_zh.jpg" style="zoom:20%;" /> | <img src="https://images.tuyacn.com/app/aiwen/tuya-smart-planter/user_info_zh.jpg" style="zoom:20%;" /> |
|                            EZ配网                            |                           设备列表                           |                           控制面板                           |
| <img src="https://images.tuyacn.com/app/aiwen/tuya-smart-planter/network_zh.jpg" style="zoom:20%;" /> | <img src="https://images.tuyacn.com/app/aiwen/tuya-smart-planter/device_list_zh.jpg" style="zoom:20%;" />| <img src="https://images.tuyacn.com/app/aiwen/tuya-smart-planter/device_control_zh.jpg" style="zoom:20%;" /> |

 

#### 三、用户管理模块

用户管理模块涉及到用户注册登录以及密码重置，关于用户管理更多信息说明以及Api接口调用，请查阅[用户管理](https://developer.tuya.com/cn/docs/app-development/android-app-sdk/user-management/usermanage?id=Ka69qtzy9l8nc)。

##### 3.1、获取验证码

```java
// phone
TuyaHomeSdk.getUserInstance().getValidateCode(String countryCode, String phoneNumber, final IValidateCallback callback);

// IValidateCallback
public interface IValidateCallback {
    void onSuccess();

    void onError(String code, String error);
}

// email
void getRegisterEmailValidateCode(String countryCode, String email, IResultCallback callback);

// IResultCallback
public interface IResultCallback {
    void onError(String var1);
    void onSuccess();
}
```
##### 3.2、用户注册

```java
// phone
TuyaHomeSdk.getUserInstance().registerAccountWithPhone(final String countryCode, final String phoneNumber, final String passwd, final String code, final IRegisterCallback callback);

// email 
TuyaHomeSdk.getUserInstance().registerAccountWithEmail(final String countryCode, final String email, final String passwd, final String code, final IRegisterCallback callback);

// IRegisterCallback
public interface IRegisterCallback {
    void onSuccess(User user);

    void onError(String code, String error);
}
```

##### 3.3、用户登录

```java
// phone
TuyaHomeSdk.getUserInstance().loginWithPhonePassword(String countryCode, String phone, String passwd, final ILoginCallback callback);
// email
TuyaHomeSdk.getUserInstance().loginWithEmail(String countryCode, String email, String passwd, final ILoginCallback callback);

// ILoginCallback
public interface ILoginCallback {
    void onSuccess(User user);

    void onError(String code, String error);
}
```

##### 3.4、用户登出

```java
TuyaHomeSdk.getUserInstance().logout(new ILogoutCallback() {
  @Override
  public void onSuccess() {
    
  }

  @Override
  public void onError(String errorCode, String errorMsg) {
  }
});
```

> 如果在注册或者登录的时候提示权限不足，请检查是否正确完成了项目配置步骤。

#### 四、家庭管理模块

涂鸦设备在添加之前，需要创建家庭，本Demo仅在程序启动的时候，根据用户的手机信息创建了一个默认家庭，关于家庭管理的更多信息以及相关Api接口，请查阅[家庭管理](https://developer.tuya.com/cn/docs/app-development/android-app-sdk/home-management/homemanage?id=Ka6kjkgere4ae)。

##### 4.1、获取家庭列表

```java
void queryHomeList(ITuyaGetHomeListCallback callback)
  
 // ITuyaGetHomeListCallback
public interface ITuyaGetHomeListCallback {
    void onSuccess(List<HomeBean> homeBeans);

    void onError(String errorCode, String error);
}
```

##### 4.2、创建家庭

```java
void createHome(String name, double lon, double lat, String geoName, List<String> rooms, ITuyaHomeResultCallback callback)
  
// ITuyaHomeResultCallback
public interface ITuyaHomeResultCallback {
    void onSuccess(HomeBean bean);

    void onError(String errorCode, String errorMsg);
}
```



#### 五、设备配网模块

智能WiFi植物生长机支持WiFi模式下EZ配网、AP配网，本Demo仅提供了EZ配网示例，更多设备配网信息以及API接口，请查阅[设备配网](https://developer.tuya.com/cn/docs/app-development/android-app-sdk/wifinetwork?id=Ka6ki8lbwu82c)。

开始配网之前，SDK 需要在联网状态下从涂鸦云获取配网 Token，Token 的有效期为10分钟，且配置成功后就会失效（再次配网需要重新获取）。

##### 5.1、获取Token

```java
TuyaHomeSdk.getActivatorInstance().getActivatorToken(homeId, 
        new ITuyaActivatorGetToken() {
        
            @Override
            public void onSuccess(String token) {
            
            }
            
            @Override
            public void onFailure(String s, String s1) {
            
            }
        });
```



**参数说明**

| 参数   | 说明                      |
| :----- | :------------------------ |
| homeId | 家庭 ID，详情参考家庭管理 |

```java
ActivatorBuilder builder = new ActivatorBuilder()
        .setSsid(ssid)
        .setContext(context)
        .setPassword(password)
        .setActivatorModel(ActivatorModelEnum.TY_EZ)
        .setTimeOut(timeout)
        .setToken(token)
        .setListener(new ITuyaSmartActivatorListener() {
            
                @Override
                public void onError(String errorCode, String errorMsg) {
                    
                }
                
                @Override
                public void onActiveSuccess(DeviceBean devResp) {
                    //多个设备同时配网，将多次回调
                }
                
                @Override
                public void onStep(String step, Object data) {
                    
                }
            }
        ));
```

**参数说明**

| 参数           | 说明                                              |
| :------------- | :------------------------------------------------ |
| token          | 配网所需要的激活 key                              |
| context        | 需要传入 activity 的 context                      |
| ssid           | 配网之后，设备工作WiFi的名称（家庭网络）          |
| password       | 配网之后，设备工作WiFi的密码（家庭网络）          |
| activatorModel | 配网模式，EZ 模式请传入：ActivatorModelEnum.TY_EZ |
| timeout        | 配网的超时时间设置，默认是 100s，单位是秒         |

##### 5.2、配网方法调用

```java
ITuyaActivator mTuyaActivator = TuyaHomeSdk.getActivatorInstance().newMultiActivator(builder);
//开始配网
mTuyaActivator.start();
//停止配网
mTuyaActivator.stop(); 
//退出页面销毁一些缓存和监听
mTuyaActivator.onDestroy();
```



#### 六、设备控制

当设备配网成功后，可以在设备列表查看到已配网的植物生长机设备，选中设备，进入控制面板,关于设备更多信息以及API接口请查阅[设备管理](https://developer.tuya.com/cn/docs/app-development/android-app-sdk/device-management/devicemanage?id=Ka6ki8r2rfiuu)。

##### 6.1、获取设备列表

设备控制必须先初始化数据，调用下面的方法获取家庭下的设备信息，每次 App 存活期间初始化一次就可以了，此外切换家庭也需要进行初始化。

```java
TuyaHomeSdk.newHomeInstance(homeId).getHomeDetail(new ITuyaHomeResultCallback() {
    @Override
    public void onSuccess(HomeBean homeBean) {
    	
    }

    @Override
    public void onError(String errorCode, String errorMsg) {

    }
});
```

该接口的 onSuccess 方法中将返回`HomeBean`，然后调用 `HomeBean` 的 `getDeviceList` 即可获得设备列表：

```java
List<DeviceBean> deviceList = homeBean.getDeviceList();
```

##### 6.2、获取设备所有功能点

```java
Map<String, SchemaBean> schemaBeanList = TuyaHomeSdk.getDataInstance().getSchema(int deviceId)
```

##### 6.3、设备控制

设备控制接口功能为向设备发送功能点，来改变设备状态或功能。

```java
ITuyaDevice.publishDps(dps, callback);
```

**参数说明**

| 参数     | 说明                                        |
| -------- | ------------------------------------------- |
| dps      | data points, 设备功能点，格式为 json 字符串 |
| callback | 发送控制指令是否成功的回调                  |

DeviceBean 类 dps 属性定义了设备的状态，称作数据点（ dp 点）或功能点。`dps` 字典里的每个 `key` 对应一个功能点的 `dpId`，`dpValue` 为该功能点的值。

**指令格式**

发送控制指令按照以下格式： {"(dpId)":"(dpValue)"}

**示例代码**

假设开灯的设备功能点是 101，那么开灯的控制代码如下所示：

```java
mDevice.publishDps("{\"101\": true}", new IResultCallback() {
    @Override
    public void onError(String code, String error) {
        Toast.makeText(mContext, "开灯失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(mContext, "开灯成功", Toast.LENGTH_SHORT).show();
    }
});
```

植物生长机DP控制点：

1.开关：启动、关闭设备

2.水箱抽水水泵开关：在设备启动后，开启水泵，可以往水箱中注水

3.当前温度、湿度显示

4.灯光定时：控制灯照时间

5.灯光倒计时

6.故障告警：展示故障代码

7.设置最大温度：当温度高于最大温度时，光照关闭，风扇会启动

8.设置最大湿度：当湿度高于最大湿度时，风扇会启动

9.水箱容积

10.设置光照颜色

11.设置最小温度：当温度低于最小温度时，光照打开

12.设置最小湿度：当湿度低于最小湿度时，土壤水泵会打开，从水箱中给植物浇水

13.自动光照开关

14.移除设备开关：移除当前配网设备，使设备重新进入配网模式

##### 6.4、设备移除

用于从用户设备列表中移除设备

```java
void removeDevice(IResultCallback callback);
```

##### 6.5、恢复出厂设置

用于将设备重置，恢复到出厂状态，设备恢复出厂设置后，会重新进入待配网状态（快连模式），设备的相关数据会被清除掉。

```java
void resetFactory(IResultCallback callback)；
```



#### 七、DIY 

通过智能植物生长机的控制面板可以看到，我们可以对植物生长机的开关、温度、湿度等信息进行监测和控制，在此基础上，开发者可以通过设置不同场景来对设备进行不同diy操作,比如你可以设置一个场景，当温度大于30摄氏度时候自动打开水泵开关。




问题反馈
------------------------

您可以通过**Github Issue** 或通过[**工单**](https://service.console.tuya.com)来进行反馈您所碰到的问题

LICENSE
------------------------
Tuya Android Smart Planter Sample是在MIT许可下提供的。更多信息请参考[LICENSE](LICENSE)文件

