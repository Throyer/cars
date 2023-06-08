package com.github.throyer.cars.modules.infra.middlewares;

import io.micrometer.tracing.Tracer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import io.micrometer.tracing.CurrentTraceContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.of;

@Component
@AllArgsConstructor
public class TraceFilter implements Filter {
  private static final String TRACE_ID_HEADER_NAME = "traceparent";
  public static final String DEFAULT = "00";
  
  private final Tracer tracer;

  @Override
  public void doFilter(
    ServletRequest request,
    ServletResponse servletResponse,
    FilterChain chain
  ) throws IOException, ServletException {
    
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    
    if (hasNoTraceHeader(response)) {
      if(traceIsEmpty(tracer)) {
        chain.doFilter(request, servletResponse);
        return;
      }
      
      response.setHeader(TRACE_ID_HEADER_NAME, header(tracer));      
    }
    
    chain.doFilter(request, servletResponse);
  }
  
  public static String header(Tracer tracer) {
    var context = tracer.currentTraceContext().context();
    var traceId = requireNonNull(context).traceId();
    var parentId = context.spanId();
    return format("%s-%s-%s-%s", DEFAULT, traceId, parentId, DEFAULT);
  }
  
  public static Boolean hasNoTraceHeader(HttpServletResponse response) {
    return !response.getHeaderNames().contains(TRACE_ID_HEADER_NAME);
  }
  
  public static Boolean traceIsEmpty(Tracer tracer) {
    return of(tracer)
      .map(Tracer::currentTraceContext)
      .map(CurrentTraceContext::context)
        .isEmpty();
  }
}
