package com.app.gradationback.controller;

import com.app.gradationback.aspect.annotation.ExceptionResponse;
import com.app.gradationback.domain.DeliveryDTO;
import com.app.gradationback.domain.DeliveryVO;
import com.app.gradationback.domain.PaymentCancellationVO;
import com.app.gradationback.exception.DeliveryException;
import com.app.gradationback.exception.PaymentException;
import com.app.gradationback.service.DeliveryService;
import com.app.gradationback.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payments/api/*")
public class PaymentController {

    private final PaymentService paymentService;
    private final DeliveryService deliveryService;

    @ExceptionResponse
    @Operation(summary = "결제", description = "결제 API")
    @ApiResponse(responseCode = "200", description = "결제 성공")
    @PostMapping("/payment")
    public ResponseEntity<Map<String, Object>> payment(@RequestBody Map<String, Object> paymentData) throws PaymentException {
        Map<String, Object> response = new HashMap<>();
        Optional<DeliveryDTO> foundPayment = paymentService.payment(paymentData);
        DeliveryDTO payment = foundPayment.orElseThrow(() -> new PaymentException("결제 등록 실패"));
        response.put("message", "결제 등록 성공");
        response.put("status", payment);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionResponse
    @Operation(summary = "결제취소", description = "결제 취소 API")
    @ApiResponse(responseCode = "200", description = "결제 취소 성공")
    @PostMapping("/cancel")
    public void cancelPayment(@RequestBody PaymentCancellationVO paymentCancellationVO) throws PaymentException {
        paymentService.paymentCancel(paymentCancellationVO);
    }

    @ExceptionResponse
    @Operation(summary = "결제 조회", description = "결제 조회 API")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @Parameter(
            name = "id",
            description = "결제 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/payment/{id}")
    public DeliveryDTO getPaymentById(@PathVariable Long id) throws PaymentException {
        Optional<DeliveryDTO> foundDelivery = paymentService.getPaymentById(id);
        return foundDelivery.orElseThrow(() -> new PaymentException("결제 조회 실패"));
    }

    @ExceptionResponse
    @Operation(summary = "해당 경매 결제 조회", description = "해당 경매의 결제여부를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @Parameter(
            name = "AuctionId",
            description = "결제 유저 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/payment/auction/{auctionId}")
    public ResponseEntity<Map<String, Object>> getPaymentByAuctionId(@PathVariable Long auctionId) {
        Map<String, Object> response = new HashMap<>();
        Optional<DeliveryDTO> foundAuction = paymentService.getPaymentByAuctionId(auctionId);
        if (foundAuction.isEmpty()) {
            response.put("status", false);
            response.put("message", "미결제");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.put("message", "조회 성공");
        response.put("status", foundAuction.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionResponse
    @Operation(summary = "결제 전체 조회", description = "결제 전체 조회 API")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @Parameter(
            name = "userId",
            description = "결제 유저 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/payment/all/{userId}")
    public List<DeliveryDTO> getPaymentByUserId(@PathVariable Long userId) throws PaymentException {
        List<DeliveryDTO> deliveryDTOList = paymentService.getPaymentByUserId(userId);
        if (deliveryDTOList.isEmpty()) {
            return new ArrayList<>();
        }
        return deliveryDTOList;
    }

    //    배송
    @ExceptionResponse
    @Operation(summary = "배송 조회", description = "Id 값으로 상품의 배송정보만 조회하는 API")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @Parameter(
            name = "id",
            description = "배송 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/delivery/{id}")
    public DeliveryVO getDeliveryByUserId(@PathVariable Long id) throws DeliveryException {
        Optional<DeliveryVO> foundDelivery = deliveryService.deliveryRead(id);
        return foundDelivery.orElseThrow(() -> new DeliveryException("배송 조회 실패"));
    }

    @ExceptionResponse
    @Operation(summary = "배송 수정", description = "배송정보를 수정할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "수정 성공")
    @PutMapping("modify")
    public void modify(DeliveryVO deliveryVO) throws DeliveryException {
    deliveryService.deliveryUpdate(deliveryVO);
}

}
