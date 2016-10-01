package com.yunxian.lightutils.lib.net;

import com.yunxian.lightutils.lib.bean.AccessNetResultBean;
import com.yunxian.lightutils.lib.bean.ApiRequestBean;
import com.yunxian.lightutils.lib.enumeration.AccessNetState;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.yunxian.lightutils.lib.constants.LightUtilsConstants.DEFAULT_TIMEOUT;

/**
 * Created by A Shuai on 2015/5/3.
 * 采用Java源生的URLConnection实现的访问网络的接口
 */
public final class URLConnectionNet implements INet {

    private final ApiRequestBean mBean;

    private final AccessNetResultBean mResultBean;

    private URL mURL;
    private HttpURLConnection mURLConnection;

    public URLConnectionNet(ApiRequestBean mBean) {
        this.mBean = mBean;

        mResultBean = new AccessNetResultBean();
    }

    @Override
    public AccessNetResultBean getResult() {

        switch (mBean.getNetOpeType()) {
            case GET:
                return getResultByGet();
            case POST:
                return getResultByPost();
            default:
                throw new IllegalStateException();
        }

    }

    @Override
    public AccessNetResultBean getResultByGet() {
        BufferedReader mReader = null;

        try {
            mURL = new URL(mBean.getGetUrl());
            mURLConnection = (HttpURLConnection) mURL.openConnection();

            mURLConnection.setConnectTimeout(DEFAULT_TIMEOUT);
            mURLConnection.setReadTimeout(DEFAULT_TIMEOUT);

            mReader = new BufferedReader(new InputStreamReader(mURLConnection.getInputStream(), "utf-8"));

            if (mURLConnection.getResponseCode() == 200) {

                String mStr;
                StringBuilder mSB = new StringBuilder();
                while ((mStr = mReader.readLine()) != null) {
                    mSB.append(mStr);
                }

                mResultBean.setState(AccessNetState.Success);
                mResultBean.setResult(mSB.toString());

            } else {
                mResultBean.setState(AccessNetState.ServerException);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            mResultBean.setState(AccessNetState.ClientException);
        } catch (IOException e) {
            e.printStackTrace();
            mResultBean.setState(AccessNetState.IOException);
        } finally {
            if (mURLConnection != null) {
                try {
                    mURLConnection.disconnect();
                } catch (Exception e) {
                } finally {
                    mURLConnection = null;
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
        DataOutputStream mDataOut = null;
        BufferedReader mReader = null;

        try {
            mURL = new URL(mBean.getApiUrl());

            mURLConnection = (HttpURLConnection) mURL.openConnection();
            mURLConnection.setConnectTimeout(DEFAULT_TIMEOUT);
            mURLConnection.setReadTimeout(DEFAULT_TIMEOUT);

            mURLConnection.setDoInput(true);
            mURLConnection.setDoOutput(true);
            mURLConnection.setUseCaches(false);
            mURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            mURLConnection.setRequestProperty("Charset", "UTF-8");
            mURLConnection.setRequestMethod("POST");

            mURLConnection.connect();

            mDataOut = new DataOutputStream(mURLConnection.getOutputStream());
            mDataOut.write(mBean.getPostContent().getBytes("utf-8"));
            mDataOut.flush();

            try {
                mDataOut.close();
            } catch (Exception e) {
            } finally {
                mDataOut = null;
            }

            mReader = new BufferedReader(new InputStreamReader(mURLConnection.getInputStream(), "utf-8"));

            if (mURLConnection.getResponseCode() == 200) {

                String mStr;
                StringBuilder mSB = new StringBuilder();
                while ((mStr = mReader.readLine()) != null) {
                    mSB.append(mStr);
                }

                mResultBean.setState(AccessNetState.Success);
                mResultBean.setResult(mSB.toString());

            } else {
                mResultBean.setState(AccessNetState.ServerException);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            mResultBean.setState(AccessNetState.ClientException);
        } catch (IOException e) {
            e.printStackTrace();
            mResultBean.setState(AccessNetState.IOException);
        } finally {
            if (mURLConnection != null) {
                try {
                    mURLConnection.disconnect();
                } catch (Exception e) {
                } finally {
                    mURLConnection = null;
                }
            }
            if (mDataOut != null) {
                try {
                    mDataOut.close();
                } catch (Exception e) {
                } finally {
                    mDataOut = null;
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
