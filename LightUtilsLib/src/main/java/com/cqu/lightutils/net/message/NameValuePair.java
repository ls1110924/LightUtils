package com.cqu.lightutils.net.message;

/**
 * Created by A Shuai on 2015/9/12.
 * <p>由于Android6.0基本剔除了Http包，为了保证对6.0的兼容性，对被6.0剔除的类手工进行实现。</p>
 * <p>Http请求的键值对需实现的接口</p>
 */
public interface NameValuePair {

    String getName();

    String getValue();

}
