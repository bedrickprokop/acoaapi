package br.com.acoaapi.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

//@Provider
public class ValidationExceptionMapper /*implements ExceptionMapper<ValidationException>*/ {

    @Autowired
    private MessageSource messageSource;

    /*@Override
    public Response toResponse(ValidationException e) {
        ArrayList<ValidationExceptionDTO> entityArrayList = new ArrayList<>();

        if (e instanceof ConstraintViolationException) {
            for (ConstraintViolation<?> cv : ((ConstraintViolationException) e).getConstraintViolations()) {

                //TODO analise each situation
                ArrayList<Object> paramList = new ArrayList<>();
                Map<String, Object> attributes = cv.getConstraintDescriptor().getAttributes();
                for (String key : attributes.keySet()) {
                    if (!key.equals("groups") && !key.equals("message") && !key.equals("payload")) {
                        Object o = attributes.get(key);
                        paramList.add(o);
                    }
                }

                //Recupera a mensagem no arquivo de texto pela sua chave
                String errorMessage = messageSource.getMessage(cv.getMessage(), paramList.toArray(),
                        LocaleContextHolder.getLocale());

                //Monta o DTO de error para enviar para o client passando a mensagem
                ValidationExceptionDTO dto = new ValidationExceptionDTO();
                dto.setFieldName(null != cv.getPropertyPath() ?
                        cv.getPropertyPath().toString() : null);
                dto.setInvalidValue(null != cv.getInvalidValue() ?
                        cv.getInvalidValue().toString() : null);
                dto.setErrorMessage(errorMessage);

                entityArrayList.add(dto);
            }

        } else {
            //TODO debug, test and implement - it can be ConstraintDeclarationException, for example
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
                .type(MediaType.APPLICATION_JSON)
                .entity(entityArrayList)
                .build();
    }*/
}