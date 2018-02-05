package com.zqsign.common.utils.httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;

public abstract interface HttpResponseCallBack<T>
{
  public abstract T doResponse(HttpResponse paramHttpResponse)
    throws IOException;
}
