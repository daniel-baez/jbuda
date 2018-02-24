# This is a work in progress ![travis-ci](https://travis-ci.org/daplay/jbuda.svg?branch=master)

Java client for buda.com REST API

## Usage

```java
import cl.daplay.jbuda.JBuda;

JBuda buda = new JBuda(apiKey, apiSecret);

// create order
Order order = buda.createOrder(MarketID.BTC_CLP, OrderType.BID, OrderPriceType.LIMIT, BigDecimal.ONE, BigDecimal.ONE);

// cancel order
buda.cancelOrder(order.getId());

// get all orders
List<Order> orders = buda.getOrders(MarketID.BTC_CLP);

```

```java
import cl.daplay.jbuda.JBuda;

// this allows to call public APIs only
JBuda buda = new JBuda();

// GET /markets
List<Market> markets = buda.getMarkets();
```


## Installation

```xml
<dependency>
   <groupId>cl.daplay</groupId>
   <artifactId>jbuda</artifactId>
   <version>4.3.0</version>
</dependency>
```

```groovy
compile group: 'cl.daplay', name: 'jbuda', version: '4.3.0'
```
   
# Documentation

- [Javadoc](http://docs.daplay.cl/jbuda/cl/daplay/jbuda/JBuda.html)
- [Official API Docs](http://api.buda.com/)

# Testing

To run integration tests (those ended in _IT.java), you'll need to add the following settings to your $HOME/.gradle/gradle.properties

- `jbuda.key`
- `jbuda.secret`
- `jbuda.proxy.host` OPTIONAL
- `jbuda.proxy.port` OPTIONAL

# Todo

- Remove cl.daplay.jbuda.jackson dependency
- Extract public API

# Errors

- 400 Bad Request
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found
- 405 Method Not Allowed
- 406 Not Acceptable
- 410 Gone
- 429 Too Many Requests
- 422 Unprocessable entity
- 500 Internal Server Error
- 503 Service Unavailable
