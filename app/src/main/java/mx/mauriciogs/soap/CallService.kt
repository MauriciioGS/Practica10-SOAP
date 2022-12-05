package mx.mauriciogs.soap

import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

class CallService {

    fun callApi(methodName: String, paramOne: String, paramTwo: String): String {
        var result = ""
        val SOAP_ACTION = Utils.SOAP_NAMESPACE+methodName
        val soapObject = SoapObject(Utils.SOAP_NAMESPACE, methodName)
        soapObject.addProperty("intA", paramOne)
        soapObject.addProperty("intB", paramTwo)
        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(soapObject)
        envelope.dotNet = true
        val httpTransportSE = HttpTransportSE(Utils.SOAP_URL)
        try {
            httpTransportSE.call(SOAP_ACTION, envelope)
            val soapPimitive = envelope.response
            result = soapPimitive.toString()
        } catch (exc: Exception) {
            exc.printStackTrace()
        }
        return result
    }

}