package ru.kamotive.kd.external;

import ru.kamotive.kd.entity.KdExchange;

public interface ExternalClient {

    String executeStart();
    String getLinks(KdExchange exchange);
}
