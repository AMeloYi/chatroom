package com.chatroom;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * Service class for simplified i18n message translation
 */
@Service
public class I18n {

  /** The message source. */
  @Autowired
  private MessageSource messageSource;

  /**
   * Gets the message.
   *
   * @param code
   *          the code
   * @param args
   *          the args
   * @return the message
   */
  public String getMessage(String code, Object[] args) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(code, args, locale);
  }

  /**
   * Gets the message.
   *
   * @param code
   *          the code
   * @param args
   *          the args
   * @param defaultMessage
   *          the default message
   * @return the message
   */
  public String getMessage(String code, Object[] args, String defaultMessage) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(code, args, defaultMessage, locale);
  }
}
