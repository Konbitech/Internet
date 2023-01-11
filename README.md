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
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
===========================================
```
dependencies {
	implementation 'com.github.Konbitech:Internet:0.0.1'
}
```

# How to use
```
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
			// TODO: Do something with status
			//binding.textviewFirst.text = status.name
		}
	}
}
```
