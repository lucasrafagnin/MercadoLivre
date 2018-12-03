package br.com.mercadolivre.data.repository.security

import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

class TLSX509TrustManager : X509TrustManager {

    override fun checkClientTrusted(certificates: Array<X509Certificate>, authType: String) {
        try {
            certificates[0].checkValidity()
        } catch (e: Exception) {
            throw CertificateException("Certificate not valid or trusted.")
        }
    }

    override fun checkServerTrusted(certificates: Array<X509Certificate>, s: String) {
        try {
            certificates[0].checkValidity()
        } catch (e: Exception) {
            throw CertificateException("Certificate not valid or trusted.")
        }
    }

    override fun getAcceptedIssuers() = emptyArray<X509Certificate>()

}
