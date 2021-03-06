package sepe.webservicessepe;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (11.1.1.0.0, build 100408.1504.05443)

@WebService(wsdlLocation="https://apps.ika.gr/WebServicesSepeNew-WebServicesSepe-context-root/WebServicesMainMethodSoap12HttpPort?WSDL",
  targetNamespace="http://webservicessepe/", name="WebServicesMainMethod")
@XmlSeeAlso(
  { ObjectFactory.class })
public interface WebServicesMainMethod
{
  @WebMethod
  @Action(input="http://webservicessepe/WebServicesMainMethod/retrieveAuthenticationRequest",
    output="http://webservicessepe/WebServicesMainMethod/retrieveAuthenticationResponse")
  @ResponseWrapper(localName="retrieveAuthenticationResponse",
    targetNamespace="http://webservicessepe/", className="webservicessepe.RetrieveAuthenticationResponse")
  @RequestWrapper(localName="retrieveAuthentication", targetNamespace="http://webservicessepe/",
    className="webservicessepe.RetrieveAuthentication")
  @WebResult(targetNamespace="")
  public WebResponseAuthentication retrieveAuthentication(@WebParam(targetNamespace = "",
          name = "arg0")
                                                                  WebRequestAuthentication arg0);

  @WebMethod
  @Action(input="http://webservicessepe/WebServicesMainMethod/retrieveEmployerRequest",
    output="http://webservicessepe/WebServicesMainMethod/retrieveEmployerResponse")
  @ResponseWrapper(localName="retrieveEmployerResponse", targetNamespace="http://webservicessepe/",
    className="webservicessepe.RetrieveEmployerResponse")
  @RequestWrapper(localName="retrieveEmployer", targetNamespace="http://webservicessepe/",
    className="webservicessepe.RetrieveEmployer")
  @WebResult(targetNamespace="")
  public WebResponseEmployer retrieveEmployer(@WebParam(targetNamespace = "",
          name = "arg0")
                                                      WebRequestEmployer arg0);

  @WebMethod
  @Action(input="http://webservicessepe/WebServicesMainMethod/retrieveParameterRequest",
    output="http://webservicessepe/WebServicesMainMethod/retrieveParameterResponse")
  @ResponseWrapper(localName="retrieveParameterResponse", targetNamespace="http://webservicessepe/",
    className="webservicessepe.RetrieveParameterResponse")
  @RequestWrapper(localName="retrieveParameter", targetNamespace="http://webservicessepe/",
    className="webservicessepe.RetrieveParameter")
  @WebResult(targetNamespace="")
  public WebResponseParameter retrieveParameter(@WebParam(targetNamespace = "",
          name = "arg0")
                                                        WebRequestParameter arg0);

  @WebMethod
  @Action(input="http://webservicessepe/WebServicesMainMethod/retrieveEmployerBranchesRequest",
    output="http://webservicessepe/WebServicesMainMethod/retrieveEmployerBranchesResponse")
  @ResponseWrapper(localName="retrieveEmployerBranchesResponse",
    targetNamespace="http://webservicessepe/", className="webservicessepe.RetrieveEmployerBranchesResponse")
  @RequestWrapper(localName="retrieveEmployerBranches", targetNamespace="http://webservicessepe/",
    className="webservicessepe.RetrieveEmployerBranches")
  @WebResult(targetNamespace="")
  public WebResponseEmployerBranches retrieveEmployerBranches(@WebParam(targetNamespace = "",
          name = "arg0")
                                                                      WebRequestEmployerBranches arg0);

  @WebMethod
  @Action(input="http://webservicessepe/WebServicesMainMethod/retrieveEmployerInchargesRequest",
    output="http://webservicessepe/WebServicesMainMethod/retrieveEmployerInchargesResponse")
  @ResponseWrapper(localName="retrieveEmployerInchargesResponse",
    targetNamespace="http://webservicessepe/", className="webservicessepe.RetrieveEmployerInchargesResponse")
  @RequestWrapper(localName="retrieveEmployerIncharges", targetNamespace="http://webservicessepe/",
    className="webservicessepe.RetrieveEmployerIncharges")
  @WebResult(targetNamespace="")
  public WebResponseEmployerIncharges retrieveEmployerIncharges(@WebParam(targetNamespace = "",
          name = "arg0")
                                                                        WebRequestEmployerIncharges arg0);
}
