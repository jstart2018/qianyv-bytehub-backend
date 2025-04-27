package com.qianyvbytehub.auth.domain.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    // 配置 ObjectMapper
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * 设置了所有属性（字段、getter、setter）的可见性
         * 默认行为：Jackson 默认只会序列化公共字段和公共 getter/setter。
         *          如果你想序列化一个 private 或 protected 字段，或者没有 getter/setter 方法的字段，
         *          你可以通过设置可见性来使它们参与序列化和反序列化。
         * 在某些场景中需要：例如在一些复杂的 POJO 对象中，可能字段是私有的或者没有 getter/setter 方法，
         * 这时候可以通过这个配置让 Jackson 也能处理这些字段，避免序列化失败。
         */
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        /**
         * DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES :
         *            控制在反序列化时遇到未知字段（即 JSON 中存在但 Java 类中没有定义的字段）时的行为。
         * false :
         *            表示忽略未知属性，即即使 JSON 中包含 Java 类没有的字段，也不会抛出异常，
         *            反序列化依然会成功，未知字段将被忽略。
         * 这种配置通常用在需要 兼容旧数据格式 或 避免因字段变化而导致反序列化失败 的场景下
         */
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return objectMapper;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer= new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper());

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        return redisTemplate;
    }



}
