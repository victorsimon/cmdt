recaptcha {
    // These keys are generated by the ReCaptcha service
	publicKey = "6Lc9NtsSAAAAAJw-nApf-K5PNPP_eVjaZSnnd8Zm"
	privateKey = "6Lc9NtsSAAAAALrdpkk_7IRneTsoAqL8MfJH68nt"

	// Include the noscript tags in the generated captcha
	includeNoScript = true

    // Force language change. See this for more: http://code.google.com/p/recaptcha/issues/detail?id=133
    forceLanguageInURL = false
}

mailhide {
    // Generated by the Mailhide service
    publicKey = "01ez0rqi5NW_lGuOxjazHLpg=="
    privateKey = "5088d04c4136c1eccd94a6b14d2e9e65"
}

environments {
  development {
    recaptcha {
      // Set to false to disable the display of captcha
      enabled = true

      // Communicate using HTTPS
      useSecureAPI = false
    }
  }
  sandbox {
    recaptcha {
      // Set to false to disable the display of captcha
      enabled = true

      // Communicate using HTTPS
      useSecureAPI = true
    }
  }
  production {
    recaptcha {
      // Set to false to disable the display of captcha
      enabled = true

      // Communicate using HTTPS
      useSecureAPI = true
    }
  }
}