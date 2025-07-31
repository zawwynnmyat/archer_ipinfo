package com.zawmyat.ipinfo.utils;

import jakarta.servlet.http.HttpServletRequest;

public class IPUtil {
    public static String getClientIP(HttpServletRequest request) {
        String[] headers = {
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_X_FORWARDED_FOR",
                "HTTP_X_FORWARDED",
                "HTTP_X_CLUSTER_CLIENT_IP",
                "HTTP_CLIENT_IP",
                "HTTP_FORWARDED_FOR",
                "HTTP_FORWARDED",
                "HTTP_VIA",
                "REMOTE_ADDR"
        };

        for (String header : headers) {
            String ip = request.getHeader(header);
            if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
                ip = ip.split(",")[0].trim(); // handle multiple IPs
                return normalizeIPv4(ip);
            }
        }

        String ip = request.getRemoteAddr();
        return normalizeIPv4(ip);
    }

    private static String normalizeIPv4(String ip) {
        // convert IPv6 loopback (::1 or 0:0:0:0:0:0:0:1) to 127.0.0.1
        if ("::1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            return "127.0.0.1";
        }

        // optionally filter out other IPv6 addresses if needed
        if (ip.contains(":")) {
            return "127.0.0.1"; // fallback for all IPv6 addresses
        }

        return ip;
    }
}
