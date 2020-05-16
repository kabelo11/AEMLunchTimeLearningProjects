package org.redquark.demo.core.services.Impl;

import org.osgi.service.component.annotations.Component;
import static org.redquark.demo.core.constants.AppConstants.URL;
import org.redquark.demo.core.services.ReadJsonService;
import org.redquark.demo.core.utils.Network;

@Component(immediate = true, service = ReadJsonService.class)
public class ReadJsonDataImpl implements ReadJsonService {

 
	@Override
	public String getData() {
		
		String response = Network.readJson(URL);
		
		return response;
	}

}