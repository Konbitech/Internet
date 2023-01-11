# Internet

library used to monitor internet connection status
Available,
Losing,
Lost,
Unavailable,
CapabilitiesChanged,
LinkPropertiesChanged,
BlockedStatusChanged

# How to add
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

===========================================

dependencies {
	implementation 'com.github.Konbitech:Internet:Tag'
}

# How to use
val internet = Internet(requireContext())
viewLifecycleOwner.lifecycleScope.launch {
	launch {
		internet.observe().collect { status ->
			when (status) {
				InternetService.Status.CapabilitiesChanged,
				InternetService.Status.LinkPropertiesChanged,
				InternetService.Status.BlockedStatusChanged-> {
					internet.observe().collect()
				}
				else -> {}
			}
			binding.textviewFirst.text = status.name
		}
	}
}