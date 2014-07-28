opcKibana {
	def defaultHost = "opcHost"
	def defaultUser = "opcUser"
	def defaultPassword = "opcPassword"
	//Domain is local if there is none
	def defaultDomain = "local"
	//You can usually find this from an OPC Client ui
	//Matrikon OPC Simulator
	def defaultClsid = "F8582CF2-88FB-11D0-B850-00C0F0104305"
	//Only generate a message if the values are different
	def defaultDiffOnly = 'true'
	
	//OPC Polling interval
	def defaultDelay = 1000;


	//Sample groups work with the Matrikon OPC Simulator
	groups = [
		[
			host: defaultHost,
			user: defaultUser,
			password: defaultPassword,
			domain : defaultDomain,
			clsid : defaultClsid,
			diffOnly : defaultDiffOnly,
			delay : defaultDelay,

			path : "/Simulation Items/Triangle Waves/Int2",
			indexType : "Triangle"
		],
		[
			host: defaultHost,
			user: defaultUser,
			password: defaultPassword,
			domain : defaultDomain,
			clsid : defaultClsid,
			diffOnly : defaultDiffOnly,
			delay : defaultDelay,

			path : "/Simulation Items/Saw-toothed Waves/Int2",
			indexType : "SawTooth"
		],
		[
			host: defaultHost,
			user: defaultUser,
			password: defaultPassword,
			domain : defaultDomain,
			clsid : defaultClsid,
			diffOnly : defaultDiffOnly,
			delay : defaultDelay,

			path : "/Simulation Items/Random/ArrayOfString",
			indexType : "Random"
		],
		[
			host: defaultHost,
			user: defaultUser,
			password: defaultPassword,
			domain : defaultDomain,
			clsid : defaultClsid,
			diffOnly : defaultDiffOnly,
			delay : defaultDelay,

			path : "/Simulation Items/Random/String",
			indexType : "Random"
		]
	]
}
