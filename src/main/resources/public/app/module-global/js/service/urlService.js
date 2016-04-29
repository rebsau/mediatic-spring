

angular.module('ModuleGlobal').factory('urlService', [function() {
	
	//var server = 'http://10.34.10.140:8090';
	var server = 'http://localhost:8080';
	
	return {
		getLoginUrl: function() {
			return server + '/api/login';
		},
	
		getRechercheMediaUrl: function() {
			return server + '/resource/media.recherche';
		},
		
		getRechercheMediaTailleUrl: function() {
			return server + '/resource/media.recherche.taille';
		},
	
		getAccessionMediaUrl: function() {
			return server + '/resource/media.accession';
		},
		
		getModificationMediaUrl: function() {
			return server + '/resource/media.modifcation';
		},
		
		getCreationMediaUrl: function() {
			return server + '/resource/media.creation';
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
			return server + '/resource/adherent.creation';
		},
	
		getAjoutEmpruntUrl: function() {
			return server + '/resource/emprunt.ajout';
		}
		
		
		
	};
	
	
	
}]);

