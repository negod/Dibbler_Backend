/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.generics;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 * @param <T>
 */
public class MethodResponse<T> {

    private final ErrorCode error;
    private final T data;
    public final boolean hasNoErrors;
    public final boolean hasErrors;

    private MethodResponse(ErrorCode error) {
        this.error = error;
        this.data = null;
        this.hasNoErrors = false;
        this.hasErrors = true;
    }

    private MethodResponse(ErrorCode error, T data) {
        this.error = error;
        this.data = data;
        this.hasNoErrors = false;
        this.hasErrors = true;
    }

    private MethodResponse(T data) {
        this.data = data;
        this.error = null;
        this.hasNoErrors = true;
        this.hasErrors = false;
    }

    public static <T> MethodResponse<T> error(ErrorCode error, T data) {
        return new MethodResponse<>(error, data);
    }

    public static <T> MethodResponse<T> error(ErrorCode error) {
        return new MethodResponse<>(error);
    }

    public static <T> MethodResponse<T> success(T data) {
        return new MethodResponse<>(data);
    }

    public WsResponse getWsResponse() {
        if (hasErrors) {
            return new WsResponse(data, error.getErrorCode());
        } else {
            return new WsResponse(data, 200);
        }
    }

    public T getData() {
        return data;
    }

    public ErrorCode getErrorCode() {
        return error;
    }

}
