package com.scope.challenge.services;

import java.util.List;

import com.scope.challenge.model.Talk;
import com.scope.challenge.model.Track;

public interface ITalkSchedulerService {

	List<Track> schedule(List<Talk> talks);

}
