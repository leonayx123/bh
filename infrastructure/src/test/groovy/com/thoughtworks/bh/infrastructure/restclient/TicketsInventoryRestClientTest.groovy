package com.thoughtworks.bh.infrastructure.restclient

import com.alibaba.fastjson.JSON
import com.thoughtworks.bh.domain.tickets.model.OrderEntity
import com.thoughtworks.bh.infrastructure.exception.ReservedException
import com.thoughtworks.bh.infrastructure.exception.ServiceUnvaliableException
import com.thoughtworks.bh.infrastructure.restclient.dto.ReservedParamDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class TicketsInventoryRestClientTest extends Specification {
    RestTemplate restTemplate = Spy()

    TicketsInventoryRestClient ticketsInventoryRestClient

    void setup() {
        ticketsInventoryRestClient = new TicketsInventoryRestClient(restTemplate)
    }

    def "should spy TicketsInventoryRestClient reserved "() {
        given:
        def order = buildOrderForTest()
        when:
        ticketsInventoryRestClient.reserved(order)
        then:
        1 * restTemplate.postForEntity(_ as String, {
            verifyAll(it, ReservedParamDto) {
                it.flightNo == order.flightNo
                it.ticketNum == order.items.size()
            }
            it
        } as ReservedParamDto, _) >> ResponseEntity.ok().body("")

        notThrown(Exception.class)
    }

    def "should throw ServiceUnvaliableException when reserved response status 503 "() {
        given:
        def order = buildOrderForTest()
        when:
        ticketsInventoryRestClient.reserved(order)
        then:
        1 * restTemplate.postForEntity(_ as String, _ as ReservedParamDto, _) >> {
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("")
        }
        thrown(ServiceUnvaliableException.class)
    }

    def "should throw ReservedException when reserved response status 409 "() {
        given:
        def order = buildOrderForTest()
        when:
        ticketsInventoryRestClient.reserved(order)
        then:
        1 * restTemplate.postForEntity(_ as String, _ as ReservedParamDto, _) >> {
            ResponseEntity.status(HttpStatus.CONFLICT).body("")
        }
        thrown(ReservedException.class)
    }


    static OrderEntity buildOrderForTest() {
        String json = "{\n" +
                "            \"flightNo\": \"MU2111\",\n" +
                "            \"items\": [\n" +
                "                {\n" +
                "                    \"idCard\": \"6101131985111111160\",\n" +
                "                    \"name\": \"杨勋\",\n" +
                "                    \"phone\": \"15690801558\",\n" +
                "                    \"baggageWeightExpense\": 0,\n" +
                "                    \"needInsurance\": true,\n" +
                "                    \"price\": 0\n" +
                "                }\n" +
                "        ]\n" +
                "        }"
        return JSON.parseObject(json, OrderEntity.class)
    }
}
