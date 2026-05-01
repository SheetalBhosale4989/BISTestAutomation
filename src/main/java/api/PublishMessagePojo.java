package api;

import java.util.Map;

public class PublishMessagePojo {
    private String vhost;
    private String name;
    private String routing_key;
    private String payload;
    private String payload_encoding;
    private Map<String, Object> properties;

    public String getVhost() {
        return vhost;
    }

    public void setVhost(String vhost) {
        this.vhost = vhost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouting_key() {
        return routing_key;
    }

    public void setRouting_key(String routing_key) {
        this.routing_key = routing_key;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getPayload_encoding() {
        return payload_encoding;
    }

    public void setPayload_encoding(String payload_encoding) {
        this.payload_encoding = payload_encoding;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
