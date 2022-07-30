package pt.feefo.jobtitleservice.config.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicResponseDTO<TYPE> {

    @Builder.Default
    private boolean success = false;

    @Builder.Default
    private String message = "";

    @Builder.Default
    private String detail = "";

    @Builder.Default
    private Integer statusCode = 200;

    private TYPE data;

    public static BasicResponseDTO success() {
        return BasicResponseDTO.builder()
            .message("Success!")
            .success(true)
            .build();
    }

    public static BasicResponseDTO success(String message) {
        return BasicResponseDTO.builder()
            .message(message)
            .success(true)
            .build();
    }

    public static <TYPE> BasicResponseDTO<TYPE> withData(String message, TYPE data) {
        BasicResponseDTO<TYPE> result = new BasicResponseDTO<>();
        result.setMessage(message);
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public static <TYPE> BasicResponseDTO<TYPE> withData(TYPE data) {
        BasicResponseDTO<TYPE> result = new BasicResponseDTO<>();
        result.setData(data);
        result.setMessage("Success!");
        result.setSuccess(true);
        return result;
    }

    public static BasicResponseDTO fail(String message) {
        return BasicResponseDTO.builder()
            .message(message)
            .success(false)
            .build();
    }

    public static BasicResponseDTO fail(String message, String detail) {
        return BasicResponseDTO.builder()
            .message(message)
            .detail(detail)
            .success(false)
            .build();
    }

    public static BasicResponseDTO success(int statusCode, String message) {
        return BasicResponseDTO.builder()
            .message(message)
            .success(true)
            .statusCode(statusCode)
            .build();
    }

    public static BasicResponseDTO fail(int statusCode, String message) {
        return BasicResponseDTO.builder()
            .message(message)
            .success(false)
            .statusCode(statusCode)
            .build();
    }

    public static BasicResponseDTO fail(int statusCode, String message, String detail) {
        return BasicResponseDTO.builder()
            .message(message)
            .detail(detail)
            .success(false)
            .statusCode(statusCode)
            .build();
    }

}
