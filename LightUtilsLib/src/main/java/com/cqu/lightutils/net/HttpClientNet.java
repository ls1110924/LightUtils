package com.cqu.lightutils.net;

import com.cqu.lightutils.bean.AccessNetResultBean;
import com.cqu.lightutils.bean.ApiRequestBean;
import com.cqu.lightutils.enumeration.AccessNetState;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.cqu.lightutils.constants.LightUtilsConstants.DEFAULT_TIMEOUT;

/**
 * Created by A Shuai on 2015/5/3.
 * 采用HttpClient形式实现的访问网络的接口
 */
public final class HttpClientNet implements INet {

    private final ApiRequestBean mBean;

    private final AccessNetResultBean mResultBean;

    private HttpClient mHttpClient;

    public HttpClientNet(ApiRequestBean mBean) {
        this.mBean = mBean;

        mResultBean = new AccessNetResultBean();
    }

    @Override
    public AccessNetResultBean getResult() {

        mHttpClient = new DefaultHttpClient();
        mHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, DEFAULT_TIMEOUT);
        mHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, DEFAULT_TIMEOUT);

        switch (mBean.getNetOpeType()) {
            case GET:
                return getResultByGet();
            case POST:
                return getResultByPost();
            default:
                throw new IllegalArgumentException();
        }

    }

    @Override
    public AccessNetResultBean getResultByGet() {
        HttpGet mHttpGet = new HttpGet(mBean.getGetUrl());

        HttpResponse mHttpResponse = null;
        BufferedReader mReader = null;

        try {
            mHttpResponse = mHttpClient.execute(mHttpGet);

            StatusLine mStatusLine = mHttpResponse.getStatusLine();
            if (mStatusLine.getStatusCode() == 200) {
                HttpEntity mHttpEntity = mHttpResponse.getEntity();
                mReader = new BufferedReader(new InputStreamReader(mHttpEntity.getContent(), "utf-8"));
                String mStrLine = null;
                StringBuilder mSB = new StringBuilder();
                while ((mStrLine = mReader.readLine()) != null) {
                    mSB.append(mStrLine);
                }
                mResultBean.setState(AccessNetState.Success);
                mResultBean.setResult(mSB.toString());
            } else {
                mResultBean.setState(AccessNetState.ServerException);
            }

        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            mResultBean.setState(AccessNetState.NetException);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            mResultBean.setState(AccessNetState.NetException);
        } catch (IOException e) {
            e.printStackTrace();
            mResultBean.setState(AccessNetState.IOException);
        } catch (Exception e) {
            e.printStackTrace();
            mResultBean.setState(AccessNetState.Exception);
        } finally {
            if (mHttpGet != null) {
                try {
                    mHttpGet.abort();
                } catch (Exception e) {
                } finally {
                    mHttpGet = null;
                }
            }
            if (mReader != null) {
                try {
                    mReader.close();
                } catch (Exception e) {
                } finally {
                    mReader = null;
                }
            }
        }
        return mResultBean;
    }

    @Override
    public AccessNetResultBean getResultByPost() {
        HttpPost mHttpPost = new HttpPost(mBean.getApiUrl());
        HttpEntity mRequestEntity = null;

        HttpResponse mHttpResponse = null;
        BufferedReader mReader = null;

        try {
            mRequestEntity = new UrlEncodedFormEntity(mBean.getKeyValuePair());
            mHttpPost.setEntity(mRequestEntity);

            mHttpResponse = mHttpClient.execute(mHttpPost);

            StatusLine mStatusLine = mHttpResponse.getStatusLine();
            if (mStatusLine.getStatusCode() == 200) {
                HttpEntity mHttpEntity = mHttpResponse.getEntity();
                mReader = new BufferedReader(new InputStreamReader(mHttpEntity.getContent(), "utf-8"));
                String mStrLine = null;
                StringBuilder mSB = new StringBuilder();
                while ((mStrLine = mReader.readLine()) != null) {
                    mSB.append(mStrLine);
                }
                mResultBean.setState(AccessNetState.Success);
                mResultBean.setResult(mSB.toString());
            } else {
                mResultBean.setState(AccessNetState.ServerException);
            }

        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            mResultBean.setState(AccessNetState.NetException);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            mResultBean.setState(AccessNetState.NetException);
        } catch (IOException e) {
            e.printStackTrace();
            mResultBean.setState(AccessNetState.IOException);
        } catch (Exception e) {
            e.printStackTrace();
            mResultBean.setState(AccessNetState.Exception);
        } finally {
            if (mHttpPost != null) {
                try {
                    mHttpPost.abort();
                } catch (Exception e) {
                } finally {
                    mHttpPost = null;
                }
            }
            if (mReader != null) {
                try {
                    mReader.close();
                } catch (Exception e) {
                } finally {
                    mReader = null;
                }
            }
        }
        return mResultBean;
    }
}
