// No service nós temos a criação dos métodos manipular os dados da API, banco de dados, etc

package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoPorCategoriaDto
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicoService(private val repository: TopicoRepository,
                    private val topicoViewMapper: TopicoViewMapper,
                    private val topicoFormMapper: TopicoFormMapper,
                    private val notFoundMessage: String = "Topico não enconstrado!"
                    ) {

    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView>{
        // O stream abaixo serve para converter para topicoView
        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map{ t ->
            // O próprio ViewMapper está fazendo a convesão dos dados de Topico para TopicoView
            topicoViewMapper.map(t)
        }
    }

    fun listarPorId(id: Long): TopicoView{
        // Utilizamos o findById, pois este lança a exception caso não encontre o registro
        val topico = repository.findById(id)
            .orElseThrow{NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        // Converteu o form para objeto tópico, só salvar no banco de dados
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        // Faz o map para transformar em VIEW mapper
        return topicoViewMapper.map(topico)

        /* REPARE NO FLUXO: topicoForm(otimização de cadastro pro cliente) => Topico (cadastrar) e Topico => topicoViewMapper(otimização de visualização pro cliente) */
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id).orElseThrow{NotFoundException(notFoundMessage)}
        // Removendo o tópico antigo e adicionando o novo
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }
}