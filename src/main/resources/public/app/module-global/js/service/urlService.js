

angular.module('ModuleGlobal').factory('urlService', [function() {
	
	//var server = 'http://10.34.10.140:8090';
	var server = 'http://localhost:8080';
	
	return {
		getLoginUrl: function() {
			return server + '/api/login';
		},
	
		getRechercheMediaUrl: function() {
			return server + '/api/medias';
		},
		
		getRechercheMediaTailleUrl: function() {
			return server + '/resource/media.recherche.taille';
		},
	
		getAccessionMediaUrl: function() {
			return server + '/api/medias';
		},
		
		getModificationMediaUrl: function() {
			return server + '/api/medias';
		},
		
		getCreationMediaUrl: function() {
			return server + '/api/medias';
		},
		
		getEmpruntForMediaUrl: function() {
			return server + '/api/emprunts';
		},
		
		getRechercheAdherentUrl: function() {
			return server + '/api/adherent';
		},
		
		getRechercheAdherentTailleUrl: function() {
			return server + '/resource/adherent.recherche.taille';
		},
	
		getAccessionAdherentUrl: function() {
			return server + '/resource/adherent.accession';
		},
		
		getModificationAdherentUrl: function() {
			return server + '/resource/adherent.modifcation';
		},
		
		getCreationAdherentUrl: function() {
			return server + '/api/adherent';
		},
	
		getAjoutEmpruntUrl: function() {
			return server + '/api/emprunts';
		}
		
		
		
	};
	
	
	
}]);

