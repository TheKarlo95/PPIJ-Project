package hr.lordsofsmell.parfume.features.core.data.network;

import hr.lordsofsmell.parfume.features.core.data.DataSource;

/**
 * Created by Karlo Vrbic on 03.03.17.
 */
public class NetworkDataSource implements DataSource {

    private ApiService service;

    public NetworkDataSource(ApiService service) {
        this.service = service;
    }
}
