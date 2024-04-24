package br.com.swapi.api.model.bean;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import br.com.swapi.api.logger.aspects.LevelLogger;
import br.com.swapi.api.logger.aspects.InjectLog;
import br.com.swapi.api.model.constants.Constants;
import br.com.swapi.api.model.dao.FilmDao;
import br.com.swapi.api.model.vo.Film;
import br.com.swapi.api.model.vo.InputParameter;
import br.com.swapi.api.model.vo.ObjectResponse;
import br.com.swapi.api.model.vo.ReturnService;
import org.springframework.stereotype.Service;

/**
 * Classe Bean - Autenticação
 * 
 * @author Clayton Morais de Oliveira
 *
 */
@Service
public class FilmBean implements Constants {

	private static final String CACHE_FILM = "cache_film";
	
	@Autowired
	private transient FilmDao dao;

	/**
	 * Retorna a lista de todos os Filmes
	 * 
	 * @param request Objeto HttpServletRequest
	 * 
	 * @return Lista do Objeto Filme
	 */
	@InjectLog(LevelLogger.DEBUG)
	@Cacheable(value=CACHE_FILM, key = "#root.methodName")
    public ReturnService listAll(final HttpServletRequest request) {
    	  return dao.listAll(request);    	  
	}


	/**
	 * Retorna os detalhes do filme
	 * 
	 * @param request Objeto HttpServletRequest
	 * 
	 * @param id Identificador do film
	 * 
	 * @return Objeto com as informações do filme
	 */
	@InjectLog(LevelLogger.DEBUG)
	public Film findById(final HttpServletRequest request, final Integer id) {
		return dao.findById(request, id);
		
	}


	/**
	 * Altera a descrição do filme e Atualiza a versão
	 * 
	 * @param request Objeto HttpServletRequest
	 * 
	 * @param id Identificador do film
	 * 
	 * @param inputParameter Parametros a serem atualizados
	 * 
	 * @return Objeto com as informações do filme
	 */
	@InjectLog(LevelLogger.DEBUG)
	public ObjectResponse updateDescription(final HttpServletRequest request,final Integer id,final InputParameter inputParameter) {		
		return dao.updateDescription(request, dao.findById(request, id), inputParameter);
	}

}
