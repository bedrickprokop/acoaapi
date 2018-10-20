package br.com.acoaapi.exception;

//@Provider
public class ApplicationExceptionMapper /*implements ExceptionMapper<Exception>*/ {

    /*@Autowired
    private MessageSource messageSource;

    public Response toResponse(Exception e) {

        String keyMessage = "internal.error.server";
        if (null != e.getMessage()) {
            keyMessage = e.getMessage();
        }

        //Recupera a mensagem no arquivo de texto pela sua chave
        String errorMessage = messageSource.getMessage(keyMessage, null,
                LocaleContextHolder.getLocale());

        ApplicationExceptionDTO dto = new ApplicationExceptionDTO();
        dto.setErrorMessage(errorMessage);

        int statusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        if (e instanceof ApplicationException) {
            ApplicationException exception = (ApplicationException) e;
            if (null != exception.getStatusCode()) {
                statusCode = exception.getStatusCode();
            }
        }
        return Response.status(statusCode)
                .type(MediaType.APPLICATION_JSON)
                .entity(dto)
                .build();
    }*/
}