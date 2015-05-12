/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.generics;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 * @param <T>
 */
public class Response<T> {

    private final ErrorCode error;
    private final T data;

    public final boolean hasNoErrors;
    public final boolean hasErrors;

    public final boolean dataIsNotNull;
    public final boolean dataIsNull;

    private Response(ErrorCode error) {
        this.error = error;
        this.data = null;
        this.hasNoErrors = false;
        this.hasErrors = true;
        this.dataIsNotNull = false;
        this.dataIsNull = true;
    }

    private Response(ErrorCode error, T data) {

        if (data == null) {
            this.dataIsNotNull = false;
            this.dataIsNull = true;
        } else {
            this.dataIsNotNull = true;
            this.dataIsNull = false;
        }

        this.error = error;
        this.data = data;
        this.hasNoErrors = false;
        this.hasErrors = true;
    }

    private Response(T data) {

        if (data == null) {
            this.dataIsNotNull = false;
            this.dataIsNull = true;
        } else {
            this.dataIsNotNull = true;
            this.dataIsNull = false;
        }

        this.data = data;
        this.error = null;
        this.hasNoErrors = true;
        this.hasErrors = false;
    }

    public static <T> Response<T> error(ErrorCode error, T data) {
        return new Response<>(error, data);
    }

    public static <T> Response<T> error(ErrorCode error) {
        return new Response<>(error);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(data);
    }

    public WsResponse getWsResponse() {
        if (hasErrors) {
            if (data != null) {
                return new WsResponse("[ " + error.getErrorType() + " ]" + error.getErrorText() + data, error.getErrorCode());
            } else {
                return new WsResponse("[ " + error.getErrorType() + " ]" + error.getErrorText(), error.getErrorCode());
            }
        } else {
            return new WsResponse(data, 200);
        }
    }

    public T getData() {
        return data;
    }

    public ErrorCode getError() {
        return error;
    }

}
