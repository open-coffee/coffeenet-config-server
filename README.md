[![Build Status](https://travis-ci.org/coffeenet/coffeenet-config-server.svg?branch=master)](https://travis-ci.org/coffeenet/coffeenet-config-server)

# CoffeeNet Config Server

The CoffeeNet Config Server is based on `spring-cloud-config-server`
that is wrapped around with the CoffeeNet infrastructure like
logging and discovery.

## Configuration

Please take a look at https://cloud.spring.io/spring-cloud-config/spring-cloud-config.html


### Public Key

If the authorization server will sign the JWT with his
private key this application needs the public key to
verify the signature.

```yaml
config-server:
  keystore:
    enabled: false
    public-key: 'file:coffeenet-public.cert'
```

You can also lay it on the classpath (classpath:)
or use a relative path.


## Usage

Just start the config server with maven:

```bash
./mvnw clean spring-boot:run
```
