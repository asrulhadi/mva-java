/**
 * Part of Web Application Secure Coding - JSP
 * It is extremely insecure! Please do not use
 * this in any kind of production environment
 *
 * @author asrulhadi
 * @created 2015
 */
package my.mva.libs;

public class Redirect {

  private String title = "Home Page";
  private String info = null;
  private String msg = null;
  private String url = "./home";

  public Redirect() {
  }

  public void setTitle(String s) {
    this.title = s;
  }

  public String getTitle() {
    return this.title;
  }

  public void setInfo(String s) {
    this.info = s;
  }

  public String getInfo() {
    return this.info;
  }

  public void setMsg(String s) {
    this.msg = s;
  }

  public String getMsg() {
    return this.msg;
  }
  public void setUrl(String s) {
    this.url = s;
  }

  public String getUrl() {
    return this.url;
  }
}// vim: et:sta:ai:ts=2:sw=2:fen:fdm=indent:
